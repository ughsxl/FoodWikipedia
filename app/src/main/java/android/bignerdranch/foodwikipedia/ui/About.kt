package android.bignerdranch.foodwikipedia.ui

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.AboutFragmentBinding
import android.bignerdranch.foodwikipedia.ui.repository.navigator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class About: Fragment(R.layout.about_fragment) {
    private lateinit var binding: AboutFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AboutFragmentBinding.bind(view)

        setupAboutStrings()
        navigator().setAppTheme()

        binding.sendFeedbackButton.setOnClickListener { sendFeedback() }

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

    }


    private fun setupAboutStrings() {
        binding.includedActionBar.screenLabel.text = getString(R.string.about_label)

        binding.applicationName.text = getString(R.string.about_app_name, getString(R.string.app_name))
        binding.versionCode.text = getString(R.string.about_version_code, getString(R.string.version_code))
    }

    private fun sendFeedback() {
        Intent(Intent.ACTION_SEND).also {
            it.type = "text/plain"
            it.`package` = "com.google.android.gm"
            it.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.gmail)))
            it.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        val preferences = activity?.getSharedPreferences(Settings.THEME_PREFERENCES, Context.MODE_PRIVATE)
        val theme = preferences?.getString(Settings.THEME_STATE, "none")

        when (theme) {
            "Light" -> binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_light_24)
            "Dark" -> binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_dark_24)
            else -> Unit
        }
    }



    companion object {
        fun newInstance() = About()
    }

}