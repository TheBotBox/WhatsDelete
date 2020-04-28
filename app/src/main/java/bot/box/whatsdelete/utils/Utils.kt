package bot.box.whatsdelete.utils

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import java.text.SimpleDateFormat
import java.util.*

/**
 * messages from by WhatsApp application, not to add in database
 */
const val WHATSAPP = "WhatsApp" //sender_name
const val WHATSAPP_WEB = "WhatsApp Web"//sender_name
const val FINISHED_BACKUP = "Finished backup"//sender_name
const val BACKUP_PROGRESS = "Backup in progress"//sender name
const val BACKUP_PAUSED = "Backup paused" //sender name
const val YOU = "You"//sender name
const val RESTORE_MEDIA = "Restoring media"
const val CHECKING_NEW_MESSAGE = "Checking for new messages"//sender message
const val CHECKING_WEB_LOGIN = "WhatsApp Web is currently active"//sender message
const val BACKUP_INFO = "Tap for more info"//sender message
const val WAITING_FOR_WIFI = "Waiting for Wi-Fi"// sender message
const val MESSAGE_DELETED = "This message was deleted"

const val WHATSAPP_PACKAGE = "com.whatsapp"

object Utils {
    fun getActualTimeFromTimeStamp(timeStamp: Long): String {
        val dt = Date(timeStamp)
        val sdf = SimpleDateFormat("hh:mm aa", Locale.getDefault())
        return sdf.format(dt)
    }
}