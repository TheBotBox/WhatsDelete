package bot.box.whatsdelete.data.database.entity

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import bot.box.whatsdelete.data.database.typeconverters.ConversationConverter
import java.util.*

@Entity(tableName = "chatRooms")
data class Chat(
    @ColumnInfo(name = "name") @PrimaryKey @NonNull var name: String = "",
    @ColumnInfo(name = "last_message") var lastMessage: String = "",
    @ColumnInfo(name = "time") var time: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "allChats")
    @TypeConverters(ConversationConverter::class) var allMessages: ArrayList<Conversation>,
    @ColumnInfo(name = "dp", typeAffinity = ColumnInfo.BLOB) var dp: ByteArray
)