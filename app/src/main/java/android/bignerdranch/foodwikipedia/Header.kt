package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.ActionbarLightThemeBinding
import android.bignerdranch.foodwikipedia.databinding.HeaderFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.loadFont
import android.bignerdranch.foodwikipedia.extensions.navigator
import android.bignerdranch.foodwikipedia.extensions.setActionBarTitle
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment


class Header: Fragment(R.layout.header_fragment) {
    private lateinit var binding: HeaderFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HeaderFragmentBinding.bind(view)

        loadFont(requireContext(), R.font.plavea_font, binding.mainLabel)

        binding.includedActionBar.screenLabel.text = getString(R.string.app_name)

        binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_light_24)
        binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_light_24)

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.aboutButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, About.newInstance())
        }

        binding.includedActionBar.toSettingsImageButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, Settings.newInstance())
        }
    }

    companion object {
        fun newInstance() = Header()
    }



}