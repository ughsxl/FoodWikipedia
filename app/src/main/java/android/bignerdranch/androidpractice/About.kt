package android.bignerdranch.androidpractice

import android.bignerdranch.androidpractice.databinding.AboutFragmentBinding
import android.bignerdranch.androidpractice.extensions.setToolbarTitle
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed

class About: Fragment(R.layout.about_fragment) {
    private lateinit var binding: AboutFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AboutFragmentBinding.bind(view)

        setupAboutStrings()
        setToolbarTitle(activity!!, getString(R.string.about))

        binding.sendFeedbackButton.setOnClickListener { sendFeedback() }
    }


    private fun setupAboutStrings() {
       binding.applicationName.text = getString(R.string.about_app_name, getString(R.string.app_name))
       binding.versionCode.text = getString(R.string.about_version_code, getString(R.string.version_code))
    }

    private fun sendFeedback() {
        val sendIntent = Intent(Intent.ACTION_SEND).also {
            it.type = "text/plain"
            it.putExtra(Intent.EXTRA_EMAIL, arrayOf("krupkoilla4@gmail.com"))
            it.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(it)
        }
    }



    companion object {
        fun newInstance() = About()
    }

}