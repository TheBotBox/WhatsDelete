package bot.box.whatsdelete.ui.activity

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import bot.box.whatsdelete.R
import bot.box.whatsdelete.ui.base.BaseActivity
import bot.box.whatsdelete.ui.viewmodel.SplashViewModel
import bot.box.whatsdelete.utils.extension.showADialog
import bot.box.whatsdelete.utils.extension.startNewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private val mViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycle.addObserver(mViewModel)
        mViewModel._notificationPermission.observe(this, Observer { isPermissionGranted ->
            if (isPermissionGranted) {
                startNewActivity<HomeActivity>()
                this@SplashActivity.finish()
            } else {
                showADialog(
                    R.string.grant_permission,
                    R.string.allow_permission,
                    R.string.exit,
                    {
                        mViewModel.askNotificationPermission()
                    },
                    {
                        this@SplashActivity.finish()
                    })

            }

        })

    }

}



