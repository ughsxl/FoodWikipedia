package android.bignerdranch.androidpractice.extensions

import android.app.Activity
import android.content.Context
import android.os.Message
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


fun Fragment.navigator() = requireActivity() as Contract

interface Contract {
    fun launchFragment(manager: FragmentManager, fragment: Fragment)
}



fun loadFont(context: Context, font: Int, textView: TextView) {
    val typeface = ResourcesCompat.getFont(context, font)
    textView.typeface = typeface
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun setToolbarTitle(activity: FragmentActivity, label: String) {
    activity.title = label
}