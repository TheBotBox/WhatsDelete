package bot.box.whatsdelete.data.repository

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import bot.box.whatsdelete.data.database.dao.ChatDao
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.entity.ChatRoom
import io.reactivex.Maybe
import io.reactivex.Single

class RepositoryImpl(private val dao: ChatDao) : IRepository {

    override fun insertMessage(chat: Chat) = dao.insertMessage(chat)

    override fun loadChatRooms(): Single<MutableList<ChatRoom>> = dao.loadChatRooms()

    override fun fetchChatsOfUser(name: String?): Single<Chat> = dao.fetchChatsOfUser(name)

}