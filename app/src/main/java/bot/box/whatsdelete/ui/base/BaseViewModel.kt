package bot.box.whatsdelete.ui.base

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import bot.box.whatsdelete.ui.service.ChatService

abstract class BaseViewModel(private val mApplication: Application) :
    AndroidViewModel(mApplication) {

    fun isNotificationAccessGranted(): Boolean {
        val cn = ComponentName(mApplication, ChatService::class.java)
        val flat = Settings.Secure.getString(
            mApplication.contentResolver,
            "enabled_notification_listeners"
        )
        return flat != null && flat.contains(cn.flattenToString())
    }

    fun askNotificationPermission() {
        mApplication.startActivity(
            Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                .addFlags(FLAG_ACTIVITY_NEW_TASK)
        )
    }
}