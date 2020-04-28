package bot.box.whatsdelete.utils.extension
/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.Uri
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.*


inline fun <reified T : AppCompatActivity> Activity.startNewActivity() = Intent(
    this, T::class.java
).apply { startActivity(this) }


fun showPopUpMenu(menuRes: Int, view: View, listener: PopupMenu.OnMenuItemClickListener) {
    val popUp = PopupMenu(view.context, view)
    popUp.inflate(menuRes)

    popUp.setOnMenuItemClickListener(listener)
    popUp.show()
}


fun Activity.getProgressDialog(): ProgressDialog {
    return ProgressDialog(this).apply {
        setCancelable(false)
        setMessage("Please Wait...")
    }
}


fun Context.getContextCompatDrawable(@DrawableRes drawableResource: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResource)
fun Context.getContextCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Activity.getContextCompatDrawable(@DrawableRes drawableResource: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResource)
fun Activity.getContextCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)


fun FragmentActivity.showADialog(
    message: Int,
    buttonPositive: Int,
    buttonNegative: Int,
    onPositiveButton: () -> Unit,
    onNegativeButton: () -> Unit
) {
    showADialog(
        this,
        this.resources.getString(message),
        this.resources.getString(buttonPositive),
        this.resources.getString(buttonNegative),
        onPositiveButton,
        onNegativeButton
    )
}

private fun showADialog(
    context: FragmentActivity,
    message: String,
    buttonPositive: String?,
    buttonNegative: String?,
    onPositiveButton: () -> Unit,
    onNegativeButton: () -> Unit
) {
    val builder =
        AlertDialog.Builder(context)
    builder.setMessage("" + message)
    builder.setCancelable(false)
    builder.setPositiveButton(
        buttonPositive
    ) { dialog: DialogInterface?, _: Int ->
        dialog?.dismiss()
        onPositiveButton.invoke()
    }
    builder.setNegativeButton(
        buttonNegative
    ) { dialog: DialogInterface?, _: Int ->
        dialog?.dismiss()
        onNegativeButton.invoke()
    }
    builder.setOnCancelListener { dialog: DialogInterface? ->
        dialog?.dismiss()
    }
    val dialog = builder.create()
    try {
        dialog.setCanceledOnTouchOutside(false)
        if (!context.isFinishing) {
            dialog.show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}