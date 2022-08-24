package android.bignerdranch.foodwikipedia.ui.screen

import android.app.AlertDialog
import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.databinding.HeaderFragmentBinding
import android.bignerdranch.foodwikipedia.ui.repository.navigator
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import java.nio.charset.Charset


class Header : Fragment(R.layout.header_fragment) {
    private lateinit var binding: HeaderFragmentBinding

    private var selectedCategory = ""
    private var selectedCategoryIndex = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HeaderFragmentBinding.bind(view)


        setupUI()
    }


    private fun setupUI() {
        ResourcesCompat.getFont(requireContext(), R.font.plavea_font).also {
            binding.mainLabel.typeface = it
        }

        binding.includedActionBar.screenLabel.text = getString(R.string.menu_label)

        if (selectedCategory != "")
            binding.launchCategoryButton.text = "${getString(R.string.launch_category)}: $selectedCategory"
        else binding.launchCategoryButton.text = getString(R.string.launch_category)

        binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_light_24)
        binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_light_24)

        navigator().setAppTheme()

        binding.includedActionBar.arrowBack.setOnClickListener { activity?.finish() }

        binding.pickCategoryButton.setOnClickListener { showPickCategoryDialog() }
        binding.launchCategoryButton.setOnClickListener { launchCategoryOnClickListener() }

        binding.aboutButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, About.newInstance())
        }

        binding.includedActionBar.toSettingsImageButton.setOnClickListener {
            navigator().launchFragment(parentFragmentManager, Settings.newInstance())
        }
    }

    private fun getJsonStrings(): MutableMap<String, String> {
        val categoryStrings = arrayListOf(
            "dairy", "fruits", "grains", "herbs_and_spices",
            "legumes", "meat_and_freshwater_fish", "nuts", "seafood", "vegetables",
        )
        val filesIds = arrayListOf(
            R.raw.dairy_category_en, R.raw.fruits_category_en, R.raw.grains_category_en,
            R.raw.herbs_and_spices_category_en, R.raw.legumes_category_en, R.raw.meat_and_freshwater_fish_category_en,
            R.raw.nuts_category_en, R.raw.seafood_category_en, R.raw.vegetables_category_en
        )

        val jsonStrings = mutableMapOf<String, String>()
        categoryStrings.forEachIndexed { index, key ->
            val inputStream = resources.openRawResource(filesIds[index])
            val jsonString = inputStream.readBytes().toString(Charset.defaultCharset())

            jsonStrings[key] = jsonString
        }

        return jsonStrings
    }

    private fun launchCategoryOnClickListener() {
        val categoryJsonStrings = getJsonStrings()

        if (binding.launchCategoryButton.text != getString(R.string.launch_category))
            when (selectedCategory) {
                "Fruits", "Фрукты", "Фрукти" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.fruits_icon,
                            categoryJsonStrings["fruits"]!!
                        ))
                }

                "Vegetables", "Овощи", "Овочі" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.vegetables_icon,
                            categoryJsonStrings["vegetables"]!!
                        )
                    )
                }

                "Meat & Freshwater Fish", "Мясо и Речная Рыба", "М'ясо та Річкова Риба" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.meat_and_fish_icon,
                            categoryJsonStrings["meat_and_freshwater_fish"]!!
                        )
                    )
                }

                "Dairy", "Молочные продукты", "Молочні продукти" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.dairy_icon,
                            categoryJsonStrings["dairy"]!!
                        )
                    )
                }

                "Grains", "Крупы", "Крупи" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.grains_icon,
                            categoryJsonStrings["grains"]!!
                        )
                    )
                }

                "Legumes", "Бобовые", "Боби" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.legumes_icon,
                            categoryJsonStrings["legumes"]!!
                        )
                    )
                }

                "Seafood", "Морепродукты", "Морепродукти" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.seafood_icon,
                            categoryJsonStrings["seafood"]!!
                        )
                    )
                }

                "Nuts", "Орехи", "Горіхи" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.nuts_icon,
                            categoryJsonStrings["nuts"]!!
                        )
                    )
                }

                "Herbs & Spices", "Травы и Специи", "Трави та Спеції" -> {
                    navigator().launchFragment(parentFragmentManager,
                        CategoryScreen.newInstance(selectedCategory,
                            R.drawable.herbs_and_spices_icon,
                            categoryJsonStrings["herbs_and_spices"]!!
                        )
                    )
                }
            }
        else Toast.makeText(requireContext(), getString(R.string.first_pick_category_toast), Toast.LENGTH_SHORT).show()
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

    override fun onResume() {
        super.onResume()
        val preferences = activity?.getSharedPreferences(Settings.THEME_PREFERENCES, Context.MODE_PRIVATE)
        val theme = preferences?.getString(Settings.THEME_STATE, "none")

        when (theme) {
            "Light", "Светлая", "Світла" -> {
                binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_light_24)
                binding.includedActionBar.screenLabel.setTextColor(activity?.resources?.getColor(R.color.white)!!)
                binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_light_24)
            }
            "Dark", "Тёмная", "Темна" -> {
                binding.includedActionBar.arrowBack.setImageResource(R.drawable.ic_baseline_exit_to_app_dark_24)
                binding.includedActionBar.screenLabel.setTextColor(activity?.resources?.getColor(R.color.black)!!)
                binding.includedActionBar.toSettingsImageButton.setImageResource(R.drawable.ic_baseline_settings_dark_24)
            }
            else -> Unit
        }
    }

    companion object {
        fun newInstance() = Header()
    }
}