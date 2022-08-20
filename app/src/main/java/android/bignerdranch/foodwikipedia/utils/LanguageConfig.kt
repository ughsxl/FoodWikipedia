package android.bignerdranch.foodwikipedia.utils

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import java.util.*

/**
 *     Created by Krupko Illa on 18.08.2022
 */

class LanguageConfig {
    fun changeLanguage(context: Context?, languageCode: String): ContextWrapper {
        var mContext = context
        val resources = mContext?.resources

        val config = resources?.configuration
        val systemLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config?.locales?.get(0)
        } else {
            config?.locale
        }

        if (!languageCode.equals("") && !systemLocale?.language.equals(languageCode)) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                config?.setLocale(locale)
            else
                config?.locale = locale

            mContext = config?.let { context?.createConfigurationContext(it) }
        }
        return ContextWrapper(mContext)
    }
}