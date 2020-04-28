package bot.box.whatsdelete.ui.viewmodel

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.entity.ChatRoom
import bot.box.whatsdelete.data.preference.IPreference
import bot.box.whatsdelete.data.repository.IRepository
import bot.box.whatsdelete.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChatViewModel(
    application: Application,
    private val iRepository: IRepository,
    private val iPreference: IPreference
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()

    private val loadChatRooms: MutableLiveData<MutableList<ChatRoom>> = MutableLiveData()
    val _loadChatRooms: LiveData<MutableList<ChatRoom>>
        get() = loadChatRooms

    fun loadChatRooms() {
        disposable.add(
            iRepository.loadChatRooms().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    loadChatRooms.value = it
                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) disposable.dispose()
    }
}