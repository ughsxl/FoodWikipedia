package android.bignerdranch.foodwikipedia.languages_spinner_components

import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.bignerdranch.foodwikipedia.R
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class LanguageAdapter(context: Context, languageList: ArrayList<LanguageItem>): ArrayAdapter<LanguageItem>(context, 0, languageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }


    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        var mConvertView: View? = convertView

        if (mConvertView == null) {
            mConvertView = LayoutInflater.from(context).inflate(
                R.layout.custom_languages_spinner, parent, false
            )
        }

        val languageIcon = mConvertView!!.findViewById<ImageView>(R.id.language_icon)
        val languageName = mConvertView!!.findViewById<TextView>(R.id.language_text)

        val currentItem = getItem(position)

        if (currentItem != null) {
            languageIcon.setImageResource(currentItem!!.getLanguageIcon())
            languageName.text = currentItem!!.getLanguageName()
        }
        return mConvertView!!
    }
}