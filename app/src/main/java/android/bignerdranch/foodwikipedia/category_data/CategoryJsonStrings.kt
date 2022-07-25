package android.bignerdranch.foodwikipedia.category_data

import org.json.JSONObject
import org.json.simple.parser.JSONParser
import java.io.FileInputStream
import java.io.FileReader
import java.util.*

class CategoryJsonStrings {






    val vegetablesJsonString = "{\"vegetables\":" +
            "{\"description\":" +
            "\"Vegetables are parts of plants that are consumed by humans or other animals as food." +
            " The original meaning is still commonly used and is applied to plants collectively to refer to all edible plant matter, " +
            "including the flowers, fruits, stems, leaves, roots, and seeds.\"," +
            "\"main representatives\":" +
            "\"Tomato, Carrot, Potato, Cabbage, Broccoli, Cucumber, Eggplant.\"," +
            "\"representatives\":" +
            "[{\"name\":\"Artichoke\"},{\"name\":\"Basil\"},{\"name\":\"Beets\"},{\"name\":\"Broccoli\"},{\"name\":\"Cabbage\"}," +
            "{\"name\":\"Carrots\"},{\"name\":\"Celery\"},{\"name\":\"Cherry\"},{\"name\":\"Chile peppers\"},{\"name\":\"Corn\"}," +
            "{\"name\":\"Cucumber\"},{\"name\":\"Dill\"},{\"name\":\"Eggplant\"},{\"name\":\"Lettuce\"},{\"name\":\"Onion\"}," +
            "{\"name\":\"Parsnip\"},{\"name\":\"Peppers\"},{\"name\":\"Potato\"},{\"name\":\"Spinach\"},{\"name\":\"Tomato\"}," +
            "{\"name\":\"Turnips\"},{\"name\":\"Zucchini\"}]}}"

    val meatAndFreshwaterFishJsonString = "{\"meat & freshwater fish\":" +
            "{\"description\":" +
            "\"Meat is animal flesh that is eaten as food. Humans have hunted, farmed, " +
            "and scavenged animals & fish for meat since prehistoric times. " +
            "Meat is mainly composed of water, protein, and fat. It is edible raw, " +
            "but is normally eaten after it has been cooked and seasoned or processed in a variety of ways.\"" +
            ",\"main representatives\":" +
            "\"Chicken, Anchovy, Carp, Turkey, Goose, Perch, Pork.\"," +
            "\"representatives\":" +
            "[{\"name\":\"Chicken\"},{\"name\":\"Turkey\"},{\"name\":\"Duck\"},{\"name\":\"Goose\"},{" +
            "\"name\":\"Beef\"},{\"name\":\"Pork\"},{\"name\":\"Lamb\"},{\"name\":\"Mutton\"}," +
            "{\"name\":\"Goat\"},{\"name\":\"Venison\"},{\"name\":\"Rabbit\"},{\"name\":\"Anchovy\"}," +
            "{\"name\":\"Bream\"},{\"name\":\"Carp\"},{\"name\":\"Catfish\"},{\"name\":\"Pike\"}," +
            "{\"name\":\"Redfish\"},{\"name\":\"Roach\"},{\"name\":\"Silver carp\"},{\"name\":\"Trout\"}]}}"

    val dairyJsonString = "{\"dairy\":{" +
            "\"description\":\"" +
            "Dairy products are generally defined as food products that are produced from milk." +
            " They are rich sources of energy. Raw milk for processing generally comes from cows," +
            " but occasionally from other mammals such as goats, sheep, and water buffalo.\"," +
            "\"main representatives\":" +
            "\"Milk, Butter, Cheese, Yogurt, Cream, Kefir, Sour cream.\"," +
            "\"representatives\":" +
            "[{\"name\":\"Ayran\"},{\"name\":\"Butter\"},{\"name\":\"Buttermilk\"}," +
            "{\"name\":\"Cashew Milk\"},{\"name\":\"Coconut milk\"},{\"name\":\"Cheese\"}," +
            "{\"name\":\"Clotted Cream\"},{\"name\":\"Cottage Cheese\"},{\"name\":\"Cream\"}," +
            "{\"name\":\"Cream Cheese\"},{\"name\":\"Goat Milk\"},{\"name\":\"Kefir\"},{\"name\":\"Sour Cream\"}," +
            "{\"name\":\"Whey Protein\"},{\"name\":\"Whipped Cream\"},{\"name\":\"Regular Milk\"},{\"name\":\"Yogurt\"}]}}"

    val grainsJsonString = "{\"grains\":{" +
            "\"description\":" +
            "\"A grain is a small, hard, dry seed – with or without an attached hull or " +
            "fruit layer – harvested for human or animal consumption. " +
            "A grain crop is a grain-producing plant. The two main types of commercial grain crops " +
            "are cereals and legumes.\"," +
            "\"main representatives\":" +
            "\"Buckwheat, Rice, Wheat, Oats, Barley, Grits, Bulgur.\"," +
            "\"representatives\":" +
            "[{\"name\":\"Artek\"},{\"name\":\"Barley\"},{\"name\":\"Buckwheat\"}," +
            "{\"name\":\"Bulgur\"},{\"name\":\"Corn\"},{\"name\":\"Couscous\"}," +
            "{\"name\":\"Grits\"},{\"name\":\"Millet\"},{\"name\":\"Oats\"},{\"name\":\"Quinoa\"}," +
            "{\"name\":\"Rice\"},{\"name\":\"Wheat\"},{\"name\":\"WildRice\"}]}}"

    val legumesJsonString = "{\"legumes\":{" +
            "\"description\":" +
            "\"Legumes are grown agriculturally, primarily for human consumption, " +
            "for livestock forage and silage, and as soil-enhancing green manure." +
            " Well-known legumes include beans, soybeans, peas, chickpeas, peanuts, lentils.\"," +
            "\"main representatives\":" +
            "\"Chickpeas, Lentils, Soybeans, Kidney Beans, Peanuts.\"," +
            "\"representatives\":" +
            "[{\"name\":\"Alfalfa\"},{\"name\":\"Chickpeas\"},{\"name\":\"Green Peas\"}," +
            "{\"name\":\"Kidney Beans\"},{\"name\":\"Lentils\"},{\"name\":\"Peanuts\"}," +
            "{\"name\":\"Sugar Snap Peas\"},{\"name\":\"Soybeans\"},{\"name\":\"Split Peas\"}]}}"

    val seafoodJsonString = "{\"seafood\":{" +
            "\"description\":" +
            "\"Seafood is any form of sea life regarded as food by humans, " +
            "prominently including fish and shellfish. Shellfish include various species of " +
            "molluscs crustaceans and echinoderms. Seafood is an important source of " +
            "protein in many diets around the world, especially in coastal areas.\"," +
            "\"main representatives\":" +
            "\"Oysters, Salmon, Trout, Abalone, Mackerel, Herring.\",\"" +
            "representatives\":" +
            "[{\"name\":\"Abalone\"},{\"name\":\"Anchovies\"},{\"name\":\"Fish Roe\"}," +
            "{\"name\":\"Mackerel\"},{\"name\":\"Mussels\"},{\"name\":\"Lobster\"}," +
            "{\"name\":\"Octopus\"},{\"name\":\"Oysters\"}," +
            "{\"name\":\"Salmon\"},{\"name\":\"Sardines\"},{\"name\":\"Seaweed\"}," +
            "{\"name\":\"Shrimp\"},{\"name\":\"Sprats\"},{\"name\":\"Squid\"},{\"name\":\"Trout\"}]}}"

    val nutsJsonString = "{\"nuts\":{" +
            "\"description\":" +
            "\"Nuts are some of the healthiest foods, and they provide a wide range of " +
            "essential nutrients. Basically, they have a hard shell and an edible core. " +
            "Biologically, they have three types: nuts, seeds and legumes.\"," +
            "\"main representatives\":" +
            "\"Walnuts, Hazelnuts, Almonds, Cashew, Pistachio.\"," +
            "\"representatives\":[" +
            "{\"name\":\"Acorns\"},{\"name\":\"Almonds\"},{\"name\":\"Cashew\"},{\"name\":\"Chestnuts\"}," +
            "{\"name\":\"Hazelnuts\"},{\"name\":\"Pine Nuts\"},{\"name\":\"Pistachio\"},{\"name\":\"Walnuts\"}]}}"

    val herbsAndSpicesJsonString = "{\"herbs & Spices\":{" +
            "\"description\":" +
            "\"Herbs and spices are food or drinking additives of mostly biological origin:" +
            " herbs, fruits, and so on. It is used in nutritionally insignificant amounts, " +
            "most often in powder form, for flavoring or coloring.\"," +
            "\"main representatives\":" +
            "\"Basil, Caraway, Cardamom, Cilantro, Cloves, Dill, Paprika.\"," +
            "\"representatives\":[" +
            "{\"name\":\"Basil\"},{\"name\":\"Bay Leaf\"},{\"name\":\"Caraway\"}," +
            "{\"name\":\"Cardamom\"},{\"name\":\"ChiliPepper\"},{\"name\":\"Chives\"}," +
            "{\"name\":\"Cilantro\"},{\"name\":\"Cinnamon\"},{\"name\":\"Dill\"}," +
            "{\"name\":\"Garam Masala\"},{\"name\":\"Garlic\"},{\"name\":\"Ginger\"}," +
            "{\"name\":\"Mustard\"},{\"name\":\"Paprika\"},{\"name\":\"Parsley\"}," +
            "{\"name\":\"Pepper\"},{\"name\":\"Poppy\"},{\"name\":\"Rosemary\"}," +
            "{\"name\":\"Saffron\"},{\"name\":\"Sage\"},{\"name\":\"Sesame\"},{\"name\":\"Tarragon\"}," +
            "{\"name\":\"Thyme\"},{\"name\":\"Turmeric\"},{\"name\":\"Vanilla\"},{\"name\":\"Wasabi\"}]}}"
}
