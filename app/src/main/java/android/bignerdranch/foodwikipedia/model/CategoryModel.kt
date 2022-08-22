package android.bignerdranch.foodwikipedia.model

data class CategoryModel(
    val description: String,
    val main_representatives: String,
    val product_name: String,
    val representatives: ArrayList<ItemModel>
    ) {
}
