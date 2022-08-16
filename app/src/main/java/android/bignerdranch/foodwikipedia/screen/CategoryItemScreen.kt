package android.bignerdranch.foodwikipedia.screen

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.CategoryItemFragmentBinding
import android.bignerdranch.foodwikipedia.models.ItemModel
import android.bignerdranch.foodwikipedia.navigator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


class CategoryItemScreen : Fragment(R.layout.category_item_fragment) {
    private val binding by lazy {
        view?.let { CategoryItemFragmentBinding.bind(it) }
    }

    private var item: ItemModel? = null

    private var category = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            item = arguments?.getParcelable(KEY_ITEM)
            category = arguments?.getString(KEY_CATEGORY) ?: ""
        }

        setupUI()
    }


    private fun setupUI() {
        binding?.itemName?.text = item?.name
        binding?.itemDescription?.text = item?.description

        binding?.caloriesValue?.text = getString(R.string.calories_value, item?.calories)
        binding?.carbsValue?.text = getString(R.string.carbs_value, item?.carbs)
        binding?.fiberValue?.text = getString(R.string.fiber_value, item?.fiber)
        binding?.sugarValue?.text = getString(R.string.sugar_value, item?.sugar)
        binding?.fatsValue?.text = getString(R.string.fats_value, item?.fat)
        binding?.proteinValue?.text = getString(R.string.protein_value, item?.protein)

        binding?.vitaminAValue?.text = getString(R.string.a_value, item?.a)
        binding?.vitaminB1Value?.text = getString(R.string.b1_value, item?.`b-1`)
        binding?.vitaminB3Value?.text = getString(R.string.b3_value, item?.`b-3`)
        binding?.vitaminB6Value?.text = getString(R.string.b6_value, item?.`b-6`)
        binding?.vitaminB9Value?.text = getString(R.string.b9_value, item?.`b-9`)
        binding?.vitaminB12Value?.text = getString(R.string.b12_value, item?.`b-12`)
        binding?.vitaminCValue?.text = getString(R.string.c_value, item?.c)
        binding?.vitaminDValue?.text = getString(R.string.d_value, item?.d)
        binding?.vitaminEValue?.text = getString(R.string.e_value, item?.e)

        binding?.calciumValue?.text = getString(R.string.calcium_value, item?.calcium)
        binding?.ironValue?.text = getString(R.string.iron_value, item?.iron)
        binding?.magnesiumValue?.text = getString(R.string.magnesium_value, item?.magnesium)
        binding?.omega3Value?.text = getString(R.string.omega3_value, item?.`omega-3`)
        binding?.phosphorusValue?.text = getString(R.string.phosphorus_value, item?.phosphorus)
        binding?.sodiumValue?.text = getString(R.string.sodium_value, item?.sodium)
        binding?.zincValue?.text = getString(R.string.zinc_value, item?.zinc)
        

        binding?.includedActionBar?.screenLabel?.text = category

        binding?.includedActionBar?.arrowBack?.setOnClickListener { activity?.onBackPressed() }
        binding?.includedActionBar?.toSettingsImageButton?.setImageResource(R.drawable.ic_baseline_home_light_24)
        binding?.includedActionBar?.toSettingsImageButton?.setOnClickListener {
            navigator().replaceFragments(parentFragmentManager, Header.newInstance())
        }
    }


    companion object {
        private const val KEY_ITEM = "key_item"
        private const val KEY_CATEGORY = "key_category"

        fun newInstance(category: String, item: ItemModel): CategoryItemScreen {
            val args = Bundle()
            args.putString(KEY_CATEGORY, category)
            args.putParcelable(KEY_ITEM, item)

            val fragment = CategoryItemScreen()
            fragment.arguments = args

            return fragment
        }
    }
}