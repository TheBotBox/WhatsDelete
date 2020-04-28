package bot.box.whatsdelete.utils.extension

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

