package android.bignerdranch.androidpractice

import android.bignerdranch.androidpractice.databinding.HeaderFragmentBinding
import android.bignerdranch.androidpractice.extensions.loadFont
import android.bignerdranch.androidpractice.extensions.navigator
import android.bignerdranch.androidpractice.extensions.setToolbarTitle
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle


class Header: Fragment(R.layout.header_fragment) {
    private lateinit var binding: HeaderFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HeaderFragmentBinding.bind(view)

        loadFont(requireContext(), R.font.plavea_font, binding.mainLabel)
        setToolbarTitle(activity!!, getString(R.string.app_name))

        binding.root.forEach {
            if (it is Button || it is ImageButton)
                it.setOnClickListener(universalOnClickListener)
        }

    }



    private val universalOnClickListener = View.OnClickListener { button ->
        when(button.id) {
            R.id.aboutButton -> navigator().launchFragment(parentFragmentManager, About.newInstance())
        }
    }



    companion object {
        fun newInstance() = Header()
    }
}