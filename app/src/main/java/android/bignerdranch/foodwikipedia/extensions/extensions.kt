package android.bignerdranch.foodwikipedia.extensions

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.ActionbarLightThemeBinding
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


fun launchFragment(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .addToBackStack(null)
        .setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        .replace(R.id.fragmentContainer, fragment)
        .commit()
}

fun loadFont(context: Context, font: Int, textView: TextView) {
    val typeface = ResourcesCompat.getFont(context, font)
    textView.typeface = typeface
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
