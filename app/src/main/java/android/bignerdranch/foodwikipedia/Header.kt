package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.HeaderFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.loadFont
import android.bignerdranch.foodwikipedia.extensions.navigator
import android.bignerdranch.foodwikipedia.extensions.setToolbarTitle
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

        setupUI()
    }



    private val universalOnClickListener = View.OnClickListener { button ->
        when(button.id) {
            R.id.aboutButton -> navigator().launchFragment(
                parentFragmentManager, About.newInstance()
            )
            R.id.settingsImageButton -> navigator().launchFragment(
                parentFragmentManager, Settings.newInstance()
            )
        }
    }

    private fun setupUI() {
        loadFont(requireContext(), R.font.plavea_font, binding.mainLabel)

        setToolbarTitle(activity!!, getString(R.string.app_name))
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_exit_to_app_light_24)

        binding.root.forEach {
            if (it is Button || it is ImageButton)
                it.setOnClickListener(universalOnClickListener)
        }
    }




    companion object {
        fun newInstance() = Header()
    }



}