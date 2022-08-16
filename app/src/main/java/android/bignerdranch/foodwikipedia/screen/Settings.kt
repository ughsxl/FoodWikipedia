package android.bignerdranch.foodwikipedia.screen

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.SettingsFragmentBinding
import android.bignerdranch.foodwikipedia.navigator
import android.bignerdranch.foodwikipedia.screen.language_spinner.Language
import android.bignerdranch.foodwikipedia.screen.language_spinner.LanguageAdapter
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class Settings : Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding

    private lateinit var mLanguageList: ArrayList<Language>
    private lateinit var mLanguageAdapter: LanguageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SettingsFragmentBinding.bind(view)

        binding.includedActionBar.screenLabel.text = getString(R.string.settings_label)
        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.musicOnOfSwitch.isChecked = activity?.getSharedPreferences(MUSIC_PREFERENCES, Context.MODE_PRIVATE)
            ?.getBoolean(MUSIC_STATE, true) ?: true

        binding.musicOnOfSwitch.setOnCheckedChangeListener { _, isChecked ->
            setMusic(isChecked)
        }

        initList()
    }


    private fun setMusic(isChecked: Boolean) {
        val preferences = activity?.getSharedPreferences(MUSIC_PREFERENCES, Context.MODE_PRIVATE)
        preferences?.edit()
            ?.putBoolean(MUSIC_STATE, isChecked)
            ?.apply()

        navigator().setMusic()
    }


    private fun initList() {
        mLanguageList = ArrayList()
        mLanguageList.run {
            add(Language("English", R.drawable.english_icon))
            add(Language("Russian", R.drawable.russia_icon))
            add(Language("Ukrainian", R.drawable.ukraine_icon))
        }

        val languageSpinner = binding.languageSpinner

        mLanguageAdapter = LanguageAdapter(requireContext(), mLanguageList)
        languageSpinner.adapter = mLanguageAdapter
    }


    companion object {
        const val MUSIC_PREFERENCES = "MUSIC_PREFERENCES"
        const val MUSIC_STATE = "MUSIC_STATE"

        fun newInstance() = Settings()
    }

}




