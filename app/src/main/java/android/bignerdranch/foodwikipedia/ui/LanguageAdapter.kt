package android.bignerdranch.foodwikipedia.ui

import android.bignerdranch.foodwikipedia.R
import android.bignerdranch.foodwikipedia.model.LanguageModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 *     Created by Krupko Illa on 15.08.2022
 */

class LanguageAdapter(context: Context, languageModelList: ArrayList<LanguageModel>) :
    ArrayAdapter<LanguageModel>(context, 0, languageModelList) {

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

        val languageIcon = mConvertView?.findViewById<ImageView>(R.id.language_icon)
        val languageName = mConvertView?.findViewById<TextView>(R.id.language_text)

        val currentItem = getItem(position)

        if (currentItem != null) {
            languageIcon?.setImageResource(currentItem.languageIcon)
            languageName?.text = currentItem.languageName
        }
        return mConvertView!!
    }
}