package android.bignerdranch.foodwikipedia.ui.screen

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.CategoryFragmentBinding
import android.bignerdranch.foodwikipedia.model.CategoryModel
import android.bignerdranch.foodwikipedia.model.ItemModel
import android.bignerdranch.foodwikipedia.ui.repository.navigator
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.gson.Gson
import kotlin.system.exitProcess

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

    private var langSeparator = -1

    private lateinit var mInterstitionalAd: InterstitialAd
    private val AD_UNIT_ID = "ca-app-pub-3467896291108690/8190985944"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryFragmentBinding.bind(view)


        MobileAds.initialize(requireActivity()) {}

        mInterstitionalAd = InterstitialAd(requireActivity())
        mInterstitionalAd.adUnitId = AD_UNIT_ID

        mInterstitionalAd.loadAd(AdRequest.Builder().build())

        mInterstitionalAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                mInterstitionalAd.loadAd(AdRequest.Builder().build())
                launchCategoryItem()
            }
        }


        if (arguments != null) {
            category = arguments?.getString(CATEGORY_KEY) ?: ""
            categoryIcon = arguments?.getInt(CATEGORY_ICON_KEY) ?: 0
            categoryJsonString = arguments?.getString(CATEGORY_JSONSTRING_KEY) ?: ""
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
        val preferences = activity?.getSharedPreferences(Settings.LANG_PREFERENCES, MODE_PRIVATE)
        val currentLanguage = preferences?.getString(Settings.LANG_STATE, "en") ?: "en"

        langSeparator = when (currentLanguage) {
            "en" -> 0
            "ru" -> 1
            "uk" -> 2
            else -> exitProcess(0)
        }

        if (categoryJsonString.isNotEmpty()) {
            val categoryObject = Gson().fromJson(categoryJsonString, CategoryModel::class.java)

            categoryDescription = categoryObject.description.split('|')[langSeparator]
            categoryMainRepresentatives = categoryObject.main_representatives.split('|')[langSeparator]
            categoryItemName = categoryObject.product_name.split('|')[langSeparator]
            categoryRepresentatives = categoryObject.representatives

            for (item in categoryRepresentatives) {
                val itemName = item.name.split('|')[langSeparator]
                representativesNames += itemName
            }
        }
    }

    private fun launchInterstitionalAd() {
        if (mInterstitionalAd.isLoaded) {
            mInterstitionalAd.show()
        } else {
            launchCategoryItem()
        }
    }

    private fun launchCategoryItem() {
        for (item in categoryRepresentatives) {
            if (pickedItem == item.name.split('|')[langSeparator]) {
                navigator().launchFragment(parentFragmentManager,
                    CategoryItemScreen.newInstance(category, item))
            }
        }
    }


    private fun showCategoryItemDialog() {
        val items = representativesNames.sorted().toTypedArray()

        AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.pick_label))
            .setSingleChoiceItems(items, pickedItemIndex) { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
            }

            .setNeutralButton(getString(R.string.cancel_label), null)

            .setPositiveButton(getString(R.string.launch_category)) { dialog, _ ->
                pickedItemIndex = (dialog as AlertDialog).listView.checkedItemPosition
                pickedItem = items[pickedItemIndex]
                launchInterstitionalAd()
            }
            .create()
            .show()
    }

    override fun onResume() {
        super.onResume()

        val preferences = activity?.getSharedPreferences(Settings.THEME_PREFERENCES, Context.MODE_PRIVATE)
        val theme = preferences?.getString(Settings.THEME_STATE, "none")

        when (theme) {
            "Light", "Светлая", "Світла" -> binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_light_24)
            "Dark", "Тёмная", "Темна" -> binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_arrow_back_dark_24)
            else -> Unit
        }
    }


    companion object {
        private const val CATEGORY_KEY = "category_key"
        private const val CATEGORY_ICON_KEY = "category_icon_key"
        const val CATEGORY_JSONSTRING_KEY = "category_jsonstring_key"

        fun newInstance(category: String, categoryIcon: Int, jsonString: String): CategoryScreen {
            val args = Bundle().apply {
                putString(CATEGORY_KEY, category)
                putInt(CATEGORY_ICON_KEY, categoryIcon)
                putString(CATEGORY_JSONSTRING_KEY, jsonString)
            }

            val fragment = CategoryScreen()
            fragment.arguments = args

            return fragment
        }
    }
}