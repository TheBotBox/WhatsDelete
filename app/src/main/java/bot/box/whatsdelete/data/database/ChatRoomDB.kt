package bot.box.whatsdelete.data.database

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.content.Context
import androidx.room.*
import bot.box.whatsdelete.data.database.dao.ChatDao
import bot.box.whatsdelete.data.database.entity.Chat
import bot.box.whatsdelete.data.database.typeconverters.ChatConverter
import bot.box.whatsdelete.data.database.typeconverters.ConversationConverter

private const val DATABASE_NAME = "chatroom.db"

@Database(
    entities = [Chat::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ChatConverter::class, ConversationConverter::class)
abstract class ChatRoomDB : RoomDatabase() {

    abstract fun getDao(): ChatDao

    companion object {
        @Volatile
        private var instance: ChatRoomDB? = null
        private val LOCK: Any = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context): ChatRoomDB =
            Room.databaseBuilder(
                context.applicationContext,
                ChatRoomDB::class.java,
                DATABASE_NAME
            ).build()
    }


}