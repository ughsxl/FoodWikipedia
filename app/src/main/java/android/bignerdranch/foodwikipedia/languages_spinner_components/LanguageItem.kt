package android.bignerdranch.foodwikipedia.languages_spinner_components

import kotlin.properties.Delegates

class LanguageItem {
    private lateinit var mLanguageName: String
    private var mLanguageIcon by Delegates.notNull<Int>()

    constructor(languageName: String, languageIcon: Int) {
        mLanguageName = languageName
        mLanguageIcon = languageIcon
    }

    fun getLanguageName() = mLanguageName
    fun getLanguageIcon() = mLanguageIcon




}