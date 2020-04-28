package bot.box.whatsdelete.data.database.dao

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import androidx.room.*
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.entity.ChatRoom
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ChatDao {
    /**
     * @param chat will insert all the data of particular chat
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(chat: Chat)

    /**
     * will return fields that only required for ChatFragment
     *
     * @return details for populating @ChatFragment
     */
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT name, last_message, time, dp FROM chatRooms")
    fun loadChatRooms(): Single<MutableList<ChatRoom>>


    /**
     * @param name of user for which details needs to be fetched
     * @return all conversation of the particular user
     */
    @Query("SELECT * FROM chatRooms WHERE name=:name")
    fun fetchChatsOfUser(name: String?): Single<Chat>


}

