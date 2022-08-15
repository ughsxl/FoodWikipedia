package android.bignerdranch.foodwikipedia.models

import android.os.Parcel
import android.os.Parcelable

data class ItemModel(
    val name: String, val description: String,
    val carbs: String, val protein: String,
    val fat: String, val calories: String,
    val fiber: String, val sugar: String,

    val a: String, val `b-1`: String,
    val `b-3`: String, val `b-6`: String,
    val `b-9`: String, val `b-12`: String,
    val c: String, val d: String,
    val e: String,

    val calcium: String, val iron: String,
    val magnesium: String, val `omega-3`: String,
    val phosphorus: String, val sodium: String,
    val zinc: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(carbs)
        parcel.writeString(protein)
        parcel.writeString(fat)
        parcel.writeString(calories)
        parcel.writeString(fiber)
        parcel.writeString(sugar)
        parcel.writeString(a)
        parcel.writeString(`b-1`)
        parcel.writeString(`b-3`)
        parcel.writeString(`b-6`)
        parcel.writeString(`b-9`)
        parcel.writeString(`b-12`)
        parcel.writeString(c)
        parcel.writeString(d)
        parcel.writeString(e)
        parcel.writeString(calcium)
        parcel.writeString(iron)
        parcel.writeString(magnesium)
        parcel.writeString(`omega-3`)
        parcel.writeString(phosphorus)
        parcel.writeString(sodium)
        parcel.writeString(zinc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }
}