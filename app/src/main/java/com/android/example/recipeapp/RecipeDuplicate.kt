package com.android.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.example.recipeapp.models.Recipe
import com.android.example.recipeapp.services.RecipeService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.android.example.recipeapp.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response


private var name: String = ""
private var ingredient: String = ""
private var recipeType: String = ""
private var recipe: String = ""
private var image: String = ""
private var ID: Int = 0
private lateinit var redirectHandler: RedirectEventHandler

class RecipeDuplicate : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (arguments != null) {
            name = arguments!!.get("Name").toString()
            ingredient = arguments!!.get("Ingredient").toString()
            recipeType = arguments!!.get("RecipeType").toString()
            recipe = arguments!!.get("Recipe").toString()
            image = arguments!!.get("Image").toString()
            ID = arguments!!.get("Id") as Int
        }
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.recipe_duplicate, container, false)

        redirectHandler = view.context as RedirectEventHandler
        val etName: TextView = view.findViewById(R.id.etName)
        etName.text = name


        var ivProfle: ImageView = view.findViewById(R.id.ivImage)

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(view).load(image).apply(options).into(ivProfle);

        var etIngredient: TextView = view.findViewById(R.id.etIngredients)
        etIngredient.text = ingredient


        var etRecipe: TextView = view.findViewById(R.id.etRecipe)
        etRecipe.text = recipe

        val btnDelete: Button = view.findViewById(R.id.btn_delete)
        btnDelete.setOnClickListener {
            deleteRecipeList()
        }

        val btnDuplicate: Button = view.findViewById(R.id.btn_duplicate)
        btnDuplicate.setOnClickListener {
            //Loading data to send through interface for update Page
            val objRecipe = Recipe()
            objRecipe.name = etName.text.toString()
            objRecipe.image = image
            objRecipe.recipetype = recipeType
            objRecipe.id = ID
            objRecipe.ingredients = etIngredient.text.toString()
            objRecipe.recipe = etRecipe.text.toString()

            duplicateRecipeList(objRecipe)
        }

        return view
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.item1).setVisible(false)
        menu.findItem(R.id.item2).setVisible(false)
        menu.findItem(R.id.item3).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }

    private fun deleteRecipeList() {
        val recipeService = ServiceBuilder.buildService(RecipeService::class.java)
        val requestCall = recipeService.deleteRecipe(ID)

        requestCall.enqueue(object : retrofit2.Callback<Unit> {

            // If you receive a HTTP Response, then this method is executed
            // Your STATUS Code will decide if your Http Response is a Success or Error
            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200's
                    Toast.makeText(view?.context, "Recipe Deleted", Toast.LENGTH_LONG)
                        .show()
                    redirectHandler.redirectToRecipeList()
                }
            }

            //             Invoked in case of Network Error or Establishing connection with Server
//             or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(view?.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun duplicateRecipeList(recipe: Recipe) {
        val recipeService = ServiceBuilder.buildService(RecipeService::class.java)
        val requestCall = recipeService.addRecipe(recipe)

        requestCall.enqueue(object : retrofit2.Callback<Recipe> {

            // If you receive a HTTP Response, then this method is executed
            // Your STATUS Code will decide if your Http Response is a Success or Error
            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200's
                    Toast.makeText(view?.context, "Recipe Duplicated", Toast.LENGTH_LONG)
                        .show()
                    redirectHandler.redirectToRecipeList()
                }
            }

            //             Invoked in case of Network Error or Establishing connection with Server
//             or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<Recipe>, t: Throwable) {

                Toast.makeText(view?.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

}

