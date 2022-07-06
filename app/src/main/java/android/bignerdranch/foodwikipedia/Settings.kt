package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.SettingsFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.setToolbarTitle
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Settings: Fragment(R.layout.settings_fragment_) {
    private lateinit var binding: SettingsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SettingsFragmentBinding.bind(view)

        setToolbarTitle(activity!!, getString(R.string.settings_label))
        (activity as? AppCompatActivity)?.supportActionBar
            ?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_light_24)
    }


    companion object {
        fun newInstance() = Settings()
    }
}