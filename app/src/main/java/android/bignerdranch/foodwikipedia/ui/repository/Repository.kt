package android.bignerdranch.foodwikipedia.ui.repository

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.navigator() = requireActivity() as Repository

interface Repository {
    fun launchFragment(manager: FragmentManager, fragment: Fragment)

    fun replaceFragments(manager: FragmentManager, fragment: Fragment)

    fun setMusic()

    fun setAppTheme()
}
