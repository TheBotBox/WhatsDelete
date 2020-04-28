package bot.box.whatsdelete.ui.service

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import bot.box.whatsdelete.R
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.entity.ChatRoom
import bot.box.whatsdelete.data.database.entity.Conversation
import bot.box.whatsdelete.data.preference.IPreference
import bot.box.whatsdelete.data.repository.IRepository
import bot.box.whatsdelete.utils.*
import io.reactivex.Completable
import io.reactivex.MaybeObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.ByteArrayOutputStream


class ChatService : NotificationListenerService(), KoinComponent {
    private val TAG = ChatService::class.java.simpleName


    private val iPreference by inject<IPreference>()
    private val iRepository by inject<IRepository>()

    private val disposable = CompositeDisposable()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        if (!iPreference.isChatRecoveryServiceEnabled()) return
        if (sbn!!.notification.flags and Notification.FLAG_GROUP_SUMMARY != 0) return

        if (sbn.packageName == WHATSAPP_PACKAGE) {
            val bundle: Bundle = sbn.notification.extras
            val name: String? = bundle.getString("android.title")
            val message: String? =
                if (bundle.getCharSequence("android.text") != null) bundle.getCharSequence("android.text").toString() else ""

            val largeIcon: Bitmap = try {
                bundle.get(Notification.EXTRA_LARGE_ICON) as Bitmap
            } catch (e: Exception) {
                BitmapFactory.decodeResource(resources, R.drawable.user_icon)
            }

            if (name != null && message != null) { // do not save these messages in database as these are application generated messages
                if (name == WHATSAPP ||
                    name == WHATSAPP_WEB ||
                    name == FINISHED_BACKUP ||
                    name == BACKUP_PROGRESS ||
                    name == BACKUP_PAUSED ||
                    name == YOU ||
                    message == CHECKING_NEW_MESSAGE ||
                    message == CHECKING_WEB_LOGIN ||
                    message == BACKUP_INFO ||
                    message == WAITING_FOR_WIFI ||
                    message == RESTORE_MEDIA
                ) return
            }

            /**
             * firstly will check if user exist or not in db
             * if user does not exist, onError block will be called in that case.
             * & if user exist then we will update new message in existing message array
             */
            disposable.add(
                iRepository.fetchChatsOfUser(name).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(userFound@{ chat ->
                        val currentTime = System.currentTimeMillis()
                        /**
                         * avoid saving redundant message in db
                         * Case handled: if message in notification tray is unread
                         * and a new message comes in, then the unread message get saved again.
                         */
                        if (chat.lastMessage == message)
                            return@userFound

                        val chatList = ArrayList<Conversation>()
                        for (conversation in chat.allMessages) {
                            chatList.add(conversation)
                        }

                        val conversation = Conversation().apply {
                            this.message = message ?: ""
                            this.isDeleted = false
                            this.time = currentTime
                        }
                        chatList.add(conversation)

                        val chatModel =
                            Chat(name!!, message!!, currentTime, chatList, getByteFromDP(largeIcon))

                        iRepository.insertMessage(chatModel)
                    }, {
                        Log.d(TAG, "User with this name does not exist in db $it")
                        disposable.add(
                            Completable.fromAction {
                                val currentTime = System.currentTimeMillis()
                                val chatList = ArrayList<Conversation>()
                                val conversation = Conversation().apply {
                                    this.message = message ?: ""
                                    this.time = currentTime
                                    this.isDeleted = false
                                }
                                chatList.add(conversation)

                                val chatModel =
                                    Chat(
                                        name!!,
                                        message!!,
                                        currentTime,
                                        chatList,
                                        getByteFromDP(largeIcon)
                                    )

                                iRepository.insertMessage(chatModel)
                            }.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe({
                                Log.d(TAG, "User inserted in db")
                            }, {
                                Log.d(TAG, "Error: User is not inserted into db $it")
                            })
                        )

                    })
            )
        }
    }

    private fun getByteFromDP(dp: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        dp.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) disposable.dispose()
    }
}