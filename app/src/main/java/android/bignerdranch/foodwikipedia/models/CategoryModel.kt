package android.bignerdranch.foodwikipedia.models

data class CategoryModel(
    val description: String,
    val main_representatives: String,
    val representatives: ArrayList<ItemModel>
    ) {
}
