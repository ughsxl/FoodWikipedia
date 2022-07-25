package android.bignerdranch.foodwikipedia.screen

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.CategoryFragmentBinding
import android.bignerdranch.foodwikipedia.extensions.launchFragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.json.JSONObject

class Category: Fragment(R.layout.category_fragment) {
    private lateinit var binding: CategoryFragmentBinding

    private var category = ""
    private var categoryIcon = 0
    private var categoryJsonString = ""

    private var categoryDescription = ""
    private var categoryItemName = ""

    private var categoryMainRepresentatives = ""
    private var representatives = mutableSetOf<String>()

    private var pickedItem = ""
    private var pickedItemIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            category = arguments?.getString(CATEGORY_KEY) ?: ""
            categoryIcon = arguments?.getInt(CATEGORY_ICON_KEY) ?: 0
            categoryJsonString = arguments?.getString(CATEGORY_JSONSTRING_KEY) ?: ""
            categoryItemName = arguments?.getString(CATEGORY_ITEM_NAME_KEY) ?: ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryFragmentBinding.bind(view)

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

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }

        binding.pickItemButton.setOnClickListener { showCategoryItemDialog() }
    }


    private fun fetchCategoryInfo() {
        val reader = JSONObject(categoryJsonString)

        val jsonCategory = reader.getJSONObject(category.lowercase())
        categoryDescription = jsonCategory.getString("description")
        categoryMainRepresentatives = jsonCategory.getString("main representatives")

        val categoryRepresentatives = jsonCategory.getJSONArray("representatives")

        for (representative in 0 until categoryRepresentatives.length()) {
            val representativeObject = categoryRepresentatives.getJSONObject(representative)

            val representativeName = representativeObject.getString("name")
            representatives.add(representativeName)
        }
    }


    private fun showCategoryItemDialog() {
        val items = representatives.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Pick item")
            .setSingleChoiceItems(items, pickedItemIndex) { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
            }
            .setPositiveButton("Discover") { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
                pickedItem = items[pickedItemIndex]

                launchFragment(parentFragmentManager,
                    CategoryItem.newInstance(category, pickedItem))
            }
            .create()
            .show()
    }


    companion object {
        private const val CATEGORY_KEY = "category_key"
        private const val CATEGORY_ICON_KEY = "category_icon_key"
        private const val CATEGORY_JSONSTRING_KEY = "category_jsonstring_key"
        private const val CATEGORY_ITEM_NAME_KEY = "category_item_name_key"

        fun newInstance(category: String, categoryIcon: Int, jsonString: String, itemName: String): Category {
            val args = Bundle()
            args.putString(CATEGORY_KEY, category)
            args.putInt(CATEGORY_ICON_KEY, categoryIcon)
            args.putString(CATEGORY_JSONSTRING_KEY, jsonString)
            args.putString(CATEGORY_ITEM_NAME_KEY, itemName)

            val fragment = Category()
            fragment.arguments = args

            return fragment
        }
    }
}