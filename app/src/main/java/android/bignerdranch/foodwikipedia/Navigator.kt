package android.bignerdranch.foodwikipedia

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.navigator() = requireActivity() as Navigator

interface Navigator {
    fun launchFragment(manager: FragmentManager, fragment: Fragment)
}
