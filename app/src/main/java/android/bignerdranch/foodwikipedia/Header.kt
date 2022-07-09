package android.bignerdranch.foodwikipedia

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.databinding.HeaderFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.loadFont
import android.bignerdranch.foodwikipedia.extensions.navigator
import android.bignerdranch.foodwikipedia.extensions.showToast
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.json.JSONException
import org.json.JSONObject


class Header: Fragment(R.layout.header_fragment) {
    private lateinit var binding: HeaderFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HeaderFragmentBinding.bind(view)
        setupUI()



    }

    private fun setupUI() {
        loadFont(requireContext(), R.font.plavea_font, binding.mainLabel)

        binding.includedActionBar.screenLabel.text = getString(R.string.app_name)

        binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_light_24)
        binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_light_24)

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.pickCategoryButton.setOnClickListener { createPickCategoryDialog() }

        binding.aboutButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, About.newInstance())
        }

        binding.includedActionBar.toSettingsImageButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, Settings.newInstance())
        }
    }


    private fun createPickCategoryDialog() {
        val categories = activity?.resources?.getStringArray(R.array.categories_list)

        var selectedCategoryIndex = 0
        var selectedCategory = categories?.get(selectedCategoryIndex)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ binding.launchCategoryButton.text = getString(R.string.launch_category) }, 1000L)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pick category")
            .setSingleChoiceItems(categories, selectedCategoryIndex) { _, which ->
                selectedCategoryIndex = which
                selectedCategory = categories?.get(which)
            }
            .setPositiveButton("Pick") { _, _ ->
                binding.launchCategoryButton.text = "${binding.launchCategoryButton.text}: $selectedCategory"
            }
            .show()
    }

    companion object {
        fun newInstance() = Header()
    }



}