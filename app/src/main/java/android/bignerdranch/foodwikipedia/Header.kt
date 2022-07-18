package android.bignerdranch.foodwikipedia

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.category_data.CategoryIcons
import android.bignerdranch.foodwikipedia.category_data.CategoryJsonStrings
import android.bignerdranch.foodwikipedia.databinding.HeaderFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.launchFragment
import android.bignerdranch.foodwikipedia.extensions.loadFont
import android.bignerdranch.foodwikipedia.extensions.showToast
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


class Header: Fragment(R.layout.header_fragment) {
    private lateinit var binding: HeaderFragmentBinding

    private var selectedCategory = ""
    private var selectedCategoryIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedCategory = savedInstanceState?.getString("selected_category") ?: ""
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HeaderFragmentBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        loadFont(requireContext(), R.font.plavea_font, binding.mainLabel)

        binding.includedActionBar.screenLabel.text = getString(R.string.app_name)

        if (selectedCategory != "")
            binding.launchCategoryButton.text = "${getString(R.string.launch_category)}: $selectedCategory"
        else binding.launchCategoryButton.text = getString(R.string.launch_category)

        binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_light_24)
        binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_light_24)

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.finish() }

        binding.pickCategoryButton.setOnClickListener { showPickCategoryDialog() }
        binding.launchCategoryButton.setOnClickListener { launchCategoryOnClickListener() }

        binding.aboutButton.setOnClickListener {
            launchFragment(parentFragmentManager, About.newInstance())
        }

        binding.includedActionBar.toSettingsImageButton.setOnClickListener {
            launchFragment(parentFragmentManager, Settings.newInstance())
        }
    }


    private fun launchCategoryOnClickListener() {
        if (binding.launchCategoryButton.text != getString(R.string.launch_category))
            when (selectedCategory) {
                "Fruits" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.fruitIcon, CategoryJsonStrings.fruitsJsonString, "fruit"))
                }
                "Vegetables" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.vegetableIcon, CategoryJsonStrings.vegetablesJsonString, "vegetable"))
                }
                "Meat & Freshwater Fish" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.meatAndFishIcon, CategoryJsonStrings.meatAndFreshwaterFishJsonString, "meat or fish"))
                }

                "Dairy" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.dairyIcon, CategoryJsonStrings.dairyJsonString, "dairy"))
                }

                "Grains" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.grainsIcon, CategoryJsonStrings.grainsJsonString, "grain"))
                }

                "Legumes" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.legumesIcon, CategoryJsonStrings.legumesJsonString, "legume"))
                }

                "Seafood" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.seafoodIcon, CategoryJsonStrings.seafoodJsonString, "seafood"))
                }

                "Nuts" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.nutsIcon, CategoryJsonStrings.nutsJsonString, "nuts"))
                }

                "Herbs & Spices" -> {
                    launchFragment(parentFragmentManager,
                        Category.newInstance(selectedCategory, CategoryIcons.herbsAndSpicesIcon, CategoryJsonStrings.herbsAndSpicesJsonString, "herb or spice"))
                }
            }
        else showToast(requireContext(), "First pick a category")
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("selected_category", selectedCategory)
    }

    private fun showPickCategoryDialog() {
        val categories = activity?.resources?.getStringArray(R.array.categories_list)

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.pick_category)
            .setOnDismissListener {
                if (selectedCategory != "")
                    binding.launchCategoryButton.text = "${getString(R.string.launch_category)}: $selectedCategory"
                else binding.launchCategoryButton.text = getString(R.string.launch_category)
            }

            .setSingleChoiceItems(categories, selectedCategoryIndex) { dialog, _ ->
                selectedCategoryIndex = (dialog as AlertDialog).listView.checkedItemPosition
            }

            .setPositiveButton(R.string.pick_label) { dialog, _ ->
                selectedCategoryIndex = (dialog as AlertDialog).listView.checkedItemPosition
                selectedCategory = categories?.get(selectedCategoryIndex) ?: ""
            }
            .create()
            .show()
    }

    companion object {
        fun newInstance() = Header()
    }
}