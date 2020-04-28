package bot.box.whatsdelete.ui.viewmodel

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Application
import androidx.lifecycle.*
import bot.box.whatsdelete.ui.base.BaseViewModel

class SplashViewModel(private val mApplication: Application) : BaseViewModel(mApplication),
    LifecycleObserver {

    private val notificationPermission: MutableLiveData<Boolean> = MutableLiveData()
    val _notificationPermission: LiveData<Boolean> get() = notificationPermission

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun checkForNotificationPermission() {
        notificationPermission.value = isNotificationAccessGranted()
    }

}