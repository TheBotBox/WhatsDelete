package bot.box.whatsdelete.data.database.entity

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class ChatRoom(
    @ColumnInfo(name = "name") @PrimaryKey @NonNull var name: String = "",
    @ColumnInfo(name = "last_message") var lastMessage: String = "",
    @ColumnInfo(name = "time") var time: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "dp", typeAffinity = ColumnInfo.BLOB) var dp: ByteArray
)