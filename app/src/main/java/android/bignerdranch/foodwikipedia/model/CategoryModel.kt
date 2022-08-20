package android.bignerdranch.foodwikipedia.model

data class CategoryModel(
    val description: String,
    val main_representatives: String,
    val representatives: ArrayList<ItemModel>
    ) {
}
