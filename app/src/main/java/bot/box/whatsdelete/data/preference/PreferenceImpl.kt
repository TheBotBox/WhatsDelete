package bot.box.whatsdelete.data.preference

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


const val KEY_IS_CHAT_RECOVERY_SERVICE_ENABLED = "KEY_IS_CHAT_RECOVERY_SERVICE_ENABLED"

class PreferenceImpl(context: Context) : IPreference {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("CAMPAIGN", Context.MODE_PRIVATE)
    }

    fun clearData() {
        sharedPref.edit { clear() }
    }

    override fun isChatRecoveryServiceEnabled(): Boolean =
        sharedPref.getBoolean(KEY_IS_CHAT_RECOVERY_SERVICE_ENABLED, true)

    override fun chatRecoveryServiceStatus(status: Boolean) {
        sharedPref.edit {
            putBoolean(KEY_IS_CHAT_RECOVERY_SERVICE_ENABLED, status)
        }
    }
}