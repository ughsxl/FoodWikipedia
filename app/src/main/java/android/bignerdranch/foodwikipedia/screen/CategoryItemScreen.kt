package android.bignerdranch.foodwikipedia.screen

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.CategoryItemFragmentBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class CategoryItemScreen: Fragment(R.layout.category_item_fragment) {
    lateinit var binding: CategoryItemFragmentBinding


    private var item = ""
    private var category = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            item = arguments?.getString(KEY_ITEM) ?: ""
            category = arguments?.getString(KEY_CATEGORY) ?: ""
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CategoryItemFragmentBinding.bind(view)

        setupUI()
    }


    private fun setupUI() {
        binding.includedActionBar.screenLabel.text = category
        binding.itemName.text = item

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.onBackPressed() }
        binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_home_light_24)
        binding.includedActionBar.toSettingsImageButton.setOnClickListener {
            parentFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
            )
                replace(R.id.fragmentContainer, Header.newInstance())
            }
        }
    }



    companion object {
        private const val KEY_ITEM = "key_item"
        private const val KEY_CATEGORY = "key_category"

        fun newInstance(category: String, item: String): CategoryItemScreen {
            val args = Bundle()
            args.putString(KEY_CATEGORY, category)
            args.putString(KEY_ITEM, item)

            val fragment = CategoryItemScreen()
            fragment.arguments = args

            return fragment
        }
    }
}