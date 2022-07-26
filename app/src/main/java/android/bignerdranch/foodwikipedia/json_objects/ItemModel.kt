package android.bignerdranch.foodwikipedia.json_objects

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
)