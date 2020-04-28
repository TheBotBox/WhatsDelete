package bot.box.whatsdelete.data.database.entity

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
data class Conversation(
    var message: String = "",
    var time: Long = 0,
    var isDeleted: Boolean = false
)