package android.bignerdranch.foodwikipedia.ui

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.SettingsFragmentBinding
import android.bignerdranch.foodwikipedia.model.LanguageModel
import android.bignerdranch.foodwikipedia.ui.adapter.LanguageAdapter
import android.bignerdranch.foodwikipedia.ui.repository.navigator
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment

class Settings : Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding

    private lateinit var mLanguageModelList: ArrayList<LanguageModel>
    private lateinit var mLanguageAdapter: LanguageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SettingsFragmentBinding.bind(view)

        binding.includedActionBar.screenLabel.text = getString(R.string.settings_label)
        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.includedActionBar.toSettingsImageButton.visibility = View.INVISIBLE

        binding.musicOnOfSwitch.isChecked = activity?.getSharedPreferences(MUSIC_PREFERENCES, MODE_PRIVATE)
            ?.getBoolean(MUSIC_STATE, true) ?: true

        val pickedTheme = activity?.getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE)
            ?.getString(THEME_STATE, "none")

        when (pickedTheme) {
            getString(R.string.light_theme_radio_button), "none"  -> binding.lightThemeRadio.isChecked = true
            getString(R.string.dark_theme_radio_button) -> binding.darkThemeRadio.isChecked = true
            else -> Toast.makeText(requireContext(), getString(R.string.an_error_occurred_toast), Toast.LENGTH_SHORT).show()
        }

        navigator().setAppTheme()


        binding.musicOnOfSwitch.setOnCheckedChangeListener { _, isChecked ->
            setMusicPreferences(isChecked)
        }


        binding.themeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val preferences = activity?.getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE)
            val theme = preferences?.getString(THEME_STATE, "none")

            if (theme == getString(R.string.light_theme_radio_button)) {
                binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_dark_24)
                binding.musicState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
                binding.themeState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
                binding.languageState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
            }
            else if (theme == getString(R.string.dark_theme_radio_button)) {
                binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_light_24)
                binding.musicState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
                binding.themeState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
                binding.languageState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
            }

            val checkedRadio = group.findViewById(checkedId) as RadioButton
            setThemePreferences(checkedRadio)
            binding.includedActionBar.toSettingsImageButton.visibility = View.INVISIBLE

        }

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentItem = parent?.selectedItem
                val languageCode = (parent?.selectedItem as LanguageModel).languageCode

                val currentLanguageCode = activity?.getSharedPreferences(LANG_PREFERENCES, MODE_PRIVATE)?.getString(LANG_STATE, "en") ?: "en"
                if (languageCode != currentLanguageCode) {
                    view?.isSelected = true
                    setLanguagePreferences(languageCode)
                    activity?.recreate()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

        initList()
    }


    private fun setMusicPreferences(isChecked: Boolean) {
        val preferences = activity?.getSharedPreferences(MUSIC_PREFERENCES, MODE_PRIVATE)

        preferences?.edit()
            ?.putBoolean(MUSIC_STATE, isChecked)
            ?.apply()

        navigator().setMusic()
    }

    private fun setThemePreferences(pickedTheme: RadioButton) {
        val preferences = activity?.getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE)

        preferences?.edit()
            ?.putString(THEME_STATE, pickedTheme.text.toString())
            ?.apply()

        navigator().setAppTheme()
    }

    fun setLanguagePreferences(languageCode: String) {
        val preferences = activity?.getSharedPreferences(LANG_PREFERENCES, MODE_PRIVATE)

        preferences?.edit()
            ?.putString(LANG_STATE, languageCode)
            ?.apply()
    }

    private fun initList() {
        mLanguageModelList = ArrayList()

        mLanguageModelList.run {
            add(LanguageModel(getString(R.string.english_language), R.drawable.english_icon, "en"))
            add(LanguageModel(getString(R.string.russian_language), R.drawable.russia_icon, "ru") )
            add(LanguageModel(getString(R.string.ukrainian_language), R.drawable.ukraine_icon, "uk"))
        }

        val languageSpinner = binding.languageSpinner

        mLanguageAdapter = LanguageAdapter(requireContext(), mLanguageModelList)
        languageSpinner.adapter = mLanguageAdapter
    }

    override fun onResume() {
        super.onResume()

        val preferences = activity?.getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE)
        val theme = preferences?.getString(THEME_STATE, "none")

        if (theme == getString(R.string.dark_theme_radio_button)) {
            binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_dark_24)
            binding.musicState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
            binding.themeState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
            binding.languageState.setTextColor(activity?.resources?.getColor(R.color.black)!!)
        } else if (theme == getString(R.string.light_theme_radio_button)) {
            binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_light_24)
            binding.musicState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
            binding.themeState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
            binding.languageState.setTextColor(activity?.resources?.getColor(R.color.white)!!)
        }

        val langPrefs = activity?.getSharedPreferences(LANG_PREFERENCES, MODE_PRIVATE)
        val currentLang = langPrefs?.getString(LANG_STATE, "en") ?: "en"

        when (currentLang) {
            "en" -> binding.languageSpinner.setSelection(0)
            "ru" -> binding.languageSpinner.setSelection(1)
            "uk" -> binding.languageSpinner.setSelection(2)
        }
    }


    companion object {
        const val MUSIC_PREFERENCES = "MUSIC_PREFERENCES"
        const val MUSIC_STATE = "MUSIC_STATE"

        const val THEME_PREFERENCES = "THEME_PREFERENCES"
        const val THEME_STATE = "THEME_STATE"

        const val LANG_PREFERENCES = "LANG_PREFERENCES"
        const val LANG_STATE = "LANG_STATE"

        fun newInstance() = Settings()
    }

}