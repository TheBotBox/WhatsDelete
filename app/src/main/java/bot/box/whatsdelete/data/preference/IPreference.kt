package bot.box.whatsdelete.data.preference

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
interface IPreference {
    fun isChatRecoveryServiceEnabled(): Boolean
    fun chatRecoveryServiceStatus(status: Boolean)
}