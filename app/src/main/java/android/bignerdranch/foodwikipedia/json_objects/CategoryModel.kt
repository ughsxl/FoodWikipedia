package android.bignerdranch.foodwikipedia.json_objects

data class CategoryModel(
    val description: String,
    val main_representatives: String,
    val representatives: ArrayList<ItemModel>
    ) {
}
