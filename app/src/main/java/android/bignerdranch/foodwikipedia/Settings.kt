package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.SettingsFragmentBinding
import android.bignerdranch.foodwikipedia.languages_spinner_components.LanguageAdapter
import android.bignerdranch.foodwikipedia.languages_spinner_components.LanguageItem
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class Settings: Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding

    private lateinit var mLanguageList: ArrayList<LanguageItem>
    private lateinit var mLanguageAdapter: LanguageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SettingsFragmentBinding.bind(view)

        binding.includedActionBar.screenLabel.text = getString(R.string.settings_label)
        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        initList()

        val languageSpinner = binding.languageSpinner

        mLanguageAdapter = LanguageAdapter(requireContext(), mLanguageList)
        languageSpinner.adapter = mLanguageAdapter
    }


    private fun initList() {
        mLanguageList = ArrayList()
        mLanguageList.run {
            add(LanguageItem("English", R.drawable.english_icon))
            add(LanguageItem("Russian", R.drawable.russia_icon))
            add(LanguageItem("Ukrainian", R.drawable.ukraine_icon))
        }
    }


    companion object {
        fun newInstance() = Settings()
    }
}