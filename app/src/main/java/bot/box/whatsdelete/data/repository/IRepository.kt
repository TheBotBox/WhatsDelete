package bot.box.whatsdelete.data.repository

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.entity.ChatRoom
import io.reactivex.Maybe
import io.reactivex.Single

interface IRepository {
    /**
     * for inserting new notification into db from ChatService.kt
     */
    fun insertMessage(chat: Chat)

    /**
     * for returning all the Chat Rooms for ChatFragment.kt
     */
    fun loadChatRooms(): Single<MutableList<ChatRoom>>


    /**
     * load all conversation of the particular user
     */
    fun fetchChatsOfUser(name: String?): Single<Chat>
}