package bot.box.whatsdelete.ui.activity

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import bot.box.whatsdelete.R
import bot.box.whatsdelete.databinding.ActivityHomeBinding
import bot.box.whatsdelete.ui.base.BaseActivity
import bot.box.whatsdelete.ui.viewmodel.ChatViewModel
import bot.box.whatsdelete.utils.extension.getProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val mViewModel by viewModel<ChatViewModel>()
    private val dialog by lazy { getProgressDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

    }


}