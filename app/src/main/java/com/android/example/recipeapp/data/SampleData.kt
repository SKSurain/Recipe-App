package com.android.example.recipeapp.data

import com.android.example.recipeapp.models.Recipe
import java.util.*
import kotlin.collections.ArrayList


object SampleData {
//	TODO
// when this formula is called, save it in shared preferences
	//whenever there is any changes to this data, update in shared preference

	//NEW RECIPE LIST WITH NEW STRUCTURE

	val newRecipeLists = ArrayList<Recipe>()
//	val recipeLists = ArrayList<Recipe>()

	private var COUNT = 5


	init {

//		var ricePuddingIngredient = ArrayList<String>()
//		ricePuddingIngredient.add("3 cups almond milk")
//		ricePuddingIngredient.add("1 cup white or brown rice, uncooked")
//		ricePuddingIngredient.add("1⁄4 cup sugar")
//		ricePuddingIngredient.add("1 teaspoon vanilla")
//		ricePuddingIngredient.add("1⁄4 teaspoon almond extract")
//		ricePuddingIngredient.add("cinnamon to taste")
//		ricePuddingIngredient.add("1⁄4 cup toasted almonds")
//
//		var strRecipePudding: String =
//			"1) Combine almond milk and rice in a 2-3 quart saucepan, and bring to a boil.\n" +
//					"2) Reduce heat and simmer for 1/2 hour with the lid on until the rice is soft.\n" +
//					"3) Add sugar, vanilla, almond extract and cinnamon. Stir and serve warm.\n" +
//					"4) Refrigerate leftovers within 2 hours."
//
//		var sampleIngredientSkillet = ArrayList<String>()
//		sampleIngredientSkillet.add("1⁄3 cup vegetable oil")
//		sampleIngredientSkillet.add("3 Tablespoons honey")
//		sampleIngredientSkillet.add("1⁄4 cup powdered milk")
//		sampleIngredientSkillet.add("1 teaspoon vanilla")
//		sampleIngredientSkillet.add("4 cups uncooked, old fashioned rolled oats")
//		sampleIngredientSkillet.add("1⁄2 cup sunflower seeds")
//		sampleIngredientSkillet.add("1 cup raisins")
//
//		var strRecipeSkillet: String =
//			"1) Warm oil and honey in a skillet for one minute over medium heat (300 degrees in an electric skillet). Add powdered milk and vanilla.\n" +
//					"2) Stir in oats and sunflower seeds, and mix until coated with oil and honey mixture. Heat over medium heat. Stir until oatmeal is slightly brown.\n" +
//					"3) Take off heat. Stir in raisins.\n" +
//					"4) Cool mixture. Store in an airtight container (jar or plastic bag)."
//
//		var tunaWrapIngredient = ArrayList<String>()
//		tunaWrapIngredient.add("3 Tablespoons lime juice")
//		tunaWrapIngredient.add("2 Tablespoons mayonnaise")
//		tunaWrapIngredient.add("2 cans (5 ounces each) tuna in water, drained")
//		tunaWrapIngredient.add("2⁄3 cup cilantro, chopped and loosely packed")
//		tunaWrapIngredient.add("2 green onions or 1/2 teaspoon onion powder")
//		tunaWrapIngredient.add("1 cup red bell pepper, diced")
//		tunaWrapIngredient.add("1 jalapeno, minced (ribs and seeds removed)")
//		tunaWrapIngredient.add("5 medium flour tortillas")
//
//		var strRecipeTunaWrap: String =
//			"1) Mix lime juice and mayonnaise together in a small bowl.  Add tuna, cilantro, onions and peppers and mix until evenly coated.\n" +
//					"2) Divide tuna mixture between 5 tortillas.  Spread to edges.\n" +
//					"3) Top with lettuce and fold or roll into a wrap.\n" +
//					"4) Refrigerate leftovers within 2 hours."
//
//
//		var strPumpkinIngredient = ArrayList<String>()
//		strPumpkinIngredient.add("1 egg")
//		strPumpkinIngredient.add("1⁄2 cup canned pumpkin")
//		strPumpkinIngredient.add("1 3⁄4 cups nonfat or 1% milk")
//		strPumpkinIngredient.add("2 Tablespoons vegetable oil")
//		strPumpkinIngredient.add("2 cups flour")
//		strPumpkinIngredient.add("2 Tablespoons brown sugar")
//		strPumpkinIngredient.add("1 Tablespoon baking powder")
//		strPumpkinIngredient.add("1 teaspoon pumpkin pie spice")
//		strPumpkinIngredient.add("1 teaspoon salt")
//		strPumpkinIngredient.add("5 Tablespoons raisins (optional, to make faces)")
//
//		var strRecipePumpkin: String =
//			"1) Combine eggs, pumpkin, milk and oil in large mixing bowl.\n" +
//					"2) Add flour, brown sugar, baking powder, pumpkin pie spice and salt to egg mixture. Stir gently.\n" +
//					"3) Lightly spray a large skillet or griddle with non-stick cooking spray or lightly wipe with oil. Heat skillet or griddle over medium-high heat (300 degrees in an electric skillet). Using a 1/4 cup measure, pour batter on hot griddle.\n" +
//					"4) Put a face on the jack-o-lantern, using raisins for eyes and teeth. Drop raisins in batter while it cooks.\n" +
//					"5) Pancakes are ready to turn when tops are bubbly all over, and the edges begin to appear dry. Use a quick flip with a broad spatula to turn pancakes. Turn only once. Continue to bake until bottoms are brown and dry.\n" +
//					"6) Refrigerate leftovers within 2 hours."
//
//
//		var strBlackBeansIngredient = ArrayList<String>()
//		strBlackBeansIngredient.add("2 teaspoons oil")
//		strBlackBeansIngredient.add("8 ounces low-fat polish kielbasa sausage, cut into small pieces")
//		strBlackBeansIngredient.add("1 large onion, chopped")
//		strBlackBeansIngredient.add("1 clove garlic, minced, or 1/4 teaspoon garlic powder")
//		strBlackBeansIngredient.add("1 red bell pepper, chopped")
//		strBlackBeansIngredient.add("1 teaspoon ground cumin")
//		strBlackBeansIngredient.add("1 cup uncooked rice")
//		strBlackBeansIngredient.add("1 can (15 ounces) black beans, drained and rinsed")
//		strBlackBeansIngredient.add("2 cups water")
//
//		var strRecipeBeans: String =
//			"1) Heat oil over medium-high heat (350 degrees in an electric skillet); sauté sausage and onion until onion is clear.\n" +
//					"2) Add remaining ingredients.\n" +
//					"3) Bring to boil over high heat, reduce heat to low, cover, and simmer for 20 minutes.\n" +
//					"4) Refrigerate leftovers within 2 hours."
//
//
//		var chilliIngredient = ArrayList<String>()
//		chilliIngredient.add("1⁄2 pound lean ground meat (15% fat)")
//		chilliIngredient.add("1 medium onion, chopped")
//		chilliIngredient.add("1 can (15 ounces) kidney beans with liquid")
//		chilliIngredient.add("2 cans (14.5 ounces each) diced tomatoes with liquid")
//		chilliIngredient.add("2 Tablespoons chili powder")
//
//		var strRecipeChilli: String =
//			"1) Brown meat and onions in a large skillet over medium-high heat (350 F degrees in an electric skillet). Drain fat.\n" +
//					"2) Add undrained beans, tomatoes with liquid and chili powder.\n" +
//					"3) Reduce heat to low (250 F degrees in an electric skillet), cover and cook for 10 minutes. Serve warm.\n" +
//					"4) Refrigerate leftovers within 2 hours."
//
//		var barleyIngredient = ArrayList<String>()
//		barleyIngredient.add("1 cup dry barley")
//		barleyIngredient.add("3 cups water")
//		barleyIngredient.add("1⁄4 cup dried cranberries")
//		barleyIngredient.add("1 cup fresh blueberries")
//		barleyIngredient.add("1 cup sweet snap peas, chopped")
//
//		var strRecipeBarley: String =
//			"1) Place barley and water in a 2 or 3 quart saucepan. Bring to a boil, then turn to low. Cook covered for 45 minutes.\n" +
//					"2) Rinse cooked barley briefly in cold water. Drain.\n" +
//					"3) Add remaining ingredients. Toss well.\n" +
//					"4) Refrigerate leftovers within 2 hours."



		//This is newRecipe where recipe is no longer ARRAYLIST its just string

		newRecipeLists.add(
			Recipe(
				1,
				"Almost Rice Pudding",
				"strRecipePudding",
				"1 ounce of pepper",
				"Dessert",
				"https://foodhero.org/sites/default/files/recipe-imgs/Almond%20Rice%20pudding%20%281%29_1.jpg"
			)
		)













//		recipeLists.add(
//			0,
//			Recipe(
//				0,
//				"Skillet Granola",
//				strRecipeSkillet,
//				sampleIngredientSkillet,
//				"Breakfast",
//				"https://foodhero.org/sites/default/files/recipe-imgs/skillet%20granola_vertical.jpg"
//			)
//		)
//		recipeLists.add(
//			1,
//			Recipe(
//				1,
//				"Almost Rice Pudding",
//				strRecipePudding,
//				ricePuddingIngredient,
//				"Dessert",
//				"https://foodhero.org/sites/default/files/recipe-imgs/Almond%20Rice%20pudding%20%281%29_1.jpg"
//			)
//		)
//		recipeLists.add(
//			2,
//			Recipe(
//				2,
//				"Black Bean And Sausage",
//				strRecipeBeans,
//				strBlackBeansIngredient,
//				"Dinner",
//				"https://foodhero.org/sites/default/files/recipe-imgs/Brazilian%20Black%20Beans%20and%20Sausage1.jpg"
//			)
//		)
//		recipeLists.add(
//			3,
//			Recipe(
//				3,
//				"Tuna Cilantro",
//				strRecipeTunaWrap,
//				tunaWrapIngredient,
//				"Lunch",
//				"https://foodhero.org/sites/default/files/recipe-imgs/cilantro_lime_tuna_wrap_v.jpg"
//			)
//		)
//		recipeLists.add(
//			4,
//			Recipe(
//				4,
//				"Quick Chilli",
//				strRecipeChilli,
//				chilliIngredient,
//				"Dinner",
//				"https://foodhero.org/sites/default/files/recipe-imgs/Quickchili_RGB.jpg"
//			)
//		)
//		recipeLists.add(
//			5,
//			Recipe(
//				5,
//				"Barley Summer Salad",
//				strRecipeBarley,
//				barleyIngredient,
//				"Lunch",
//				"https://foodhero.org/sites/default/files/recipe-imgs/Barley%20Summer%20Salad.jpg"
//			)
//		)
//		recipeLists.add(
//			6,
//			Recipe(
//				6,
//				"Pumpkin Cake",
//				strRecipePumpkin,
//				strPumpkinIngredient,
//				"Breakfast",
//				"https://foodhero.org/sites/default/files/recipe-imgs/Jack-O-Lanterns_0_0.jpg"
//			)
//		)
	}

//	fun addRecipe(item: Recipe) {
//		item.id = COUNT
//		recipeLists.add(item)
//		COUNT += 1
//	}
//
//	fun getRecipeById(id: Int): Recipe? {
//		for (i in recipeLists.indices) {
//			if (recipeLists[i].id == id) {
//				return recipeLists[i]
//			}
//		}
//
//		return null
//	}
//
//	fun deleteRecipe(id: Int) {
//		var recipeToRemove: Recipe? = null
//
//		for (i in recipeLists.indices) {
//			if (recipeLists[i].id == id) {
//				recipeToRemove = recipeLists[i]
//			}
//		}
//
//		if (recipeToRemove != null) {
//			recipeLists.remove(recipeToRemove)
//		}
//	}
//
//	fun updateRecipe(recipe: Recipe) {
//		for (i in recipeLists.indices) {
//			if (recipeLists[i].id == recipe.id) {
//				val recipeToUpdate = recipeLists[i]
//
//				recipeToUpdate.name = recipe.name
//				recipeToUpdate.recipe = recipe.recipe
//				recipeToUpdate.recipetype = recipe.recipetype
//				recipeToUpdate.ingredients = recipe.ingredients
//				recipeToUpdate.image = recipe.image
//			}
//		}
//	}
}
