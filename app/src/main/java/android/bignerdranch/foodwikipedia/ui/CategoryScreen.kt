package android.bignerdranch.foodwikipedia.ui

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.CategoryFragmentBinding
import android.bignerdranch.foodwikipedia.model.CategoryModel
import android.bignerdranch.foodwikipedia.model.ItemModel
import android.bignerdranch.foodwikipedia.ui.repository.navigator
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson

class CategoryScreen: Fragment(R.layout.category_fragment) {
    private lateinit var binding: CategoryFragmentBinding

    private var category = ""
    private var categoryIcon = 0
    private var categoryJsonString = ""

    private var categoryDescription = ""
    private var categoryItemName = ""

    private var categoryMainRepresentatives = ""
    private lateinit var representativesNames: ArrayList<String>
    private lateinit var categoryRepresentatives: ArrayList<ItemModel>

    private var pickedItem = ""
    private var pickedItemIndex = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryFragmentBinding.bind(view)

        if (arguments != null) {
            category = arguments?.getString(CATEGORY_KEY) ?: ""
            categoryIcon = arguments?.getInt(CATEGORY_ICON_KEY) ?: 0
            categoryJsonString = arguments?.getString(CATEGORY_JSONSTRING_KEY) ?: ""
            categoryItemName = arguments?.getString(CATEGORY_ITEM_NAME_KEY) ?: ""
        }

        representativesNames = ArrayList<String>()
        categoryRepresentatives = ArrayList<ItemModel>()

        fetchCategoryInfo()
        setupUI()
    }

    private fun setupUI() {
        binding.includedActionBar.screenLabel.text = getString(R.string.category_label)
        binding.includedActionBar.iconImage.setImageResource(categoryIcon)

        binding.categoryName.text = category
        binding.categoryDescription.text = categoryDescription
        binding.mainRepresentations.text = categoryMainRepresentatives
        binding.pickItemTextView.text = getString(R.string.pick_any_item_label, categoryItemName)

        navigator().setAppTheme()

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.pickItemButton.setOnClickListener { showCategoryItemDialog() }
    }


    private fun fetchCategoryInfo() {
        if (categoryJsonString.isNotEmpty()) {
            val categoryObject = Gson().fromJson(categoryJsonString, CategoryModel::class.java)

            categoryDescription = categoryObject.description
            categoryMainRepresentatives = categoryObject.main_representatives
            categoryRepresentatives = categoryObject.representatives

            for (item in categoryRepresentatives) {
                val itemName = item.name
                representativesNames += itemName
            }
        }
    }


    private fun showCategoryItemDialog() {
        val items = representativesNames.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Pick item")
            .setSingleChoiceItems(items, pickedItemIndex) { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
            }
            .setPositiveButton("Discover") { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
                pickedItem = items[pickedItemIndex]

                for (item in categoryRepresentatives) {
                    if (pickedItem == item.name) {
                        navigator().launchFragment(parentFragmentManager,
                            CategoryItemScreen.newInstance(category, item))
                    }
                }
            }
            .create()
            .show()
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
        private const val CATEGORY_KEY = "category_key"
        private const val CATEGORY_ICON_KEY = "category_icon_key"
        const val CATEGORY_JSONSTRING_KEY = "category_jsonstring_key"
        private const val CATEGORY_ITEM_NAME_KEY = "category_item_name_key"

        fun newInstance(category: String, categoryIcon: Int, jsonString: String, itemName: String): CategoryScreen {
            val args = Bundle().apply {
                putString(CATEGORY_KEY, category)
                putInt(CATEGORY_ICON_KEY, categoryIcon)
                putString(CATEGORY_JSONSTRING_KEY, jsonString)
                putString(CATEGORY_ITEM_NAME_KEY, itemName)
            }

            val fragment = CategoryScreen()
            fragment.arguments = args

            return fragment
        }
    }
}