package com.android.example.recipeapp.RecipeDetails

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.example.recipeapp.R
import com.android.example.recipeapp.RedirectEventHandler
import com.android.example.recipeapp.models.Recipe
import com.android.example.skbeonpropinvest.services.PropertyService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomappbar.BottomAppBar
import com.smartherd.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

private lateinit var recipeList: Recipe
private var name: String = ""
private var ingredient: String = ""
private var recipeType: String = ""
private var recipe: String = ""
private var image: String = ""
private var ID: Int = 0
private lateinit var redirectHandler: RedirectEventHandler

class RecipeDetails : Fragment() {
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
        val view = inflater.inflate(R.layout.recipe_details, container, false)

        redirectHandler = view.context as RedirectEventHandler
        //Loading data to send through interface for update Page
        recipeList = Recipe()
        recipeList.name = name
        recipeList.image = image
        recipeList.recipetype = recipeType
        recipeList.id = ID
        recipeList.ingredients = ingredient
        recipeList.recipe = recipe

        val tvName: TextView = view.findViewById(R.id.tvName)
        tvName.text = name


        var ivProfle: ImageView = view.findViewById(R.id.profile_picture)

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(view).load(image).apply(options).into(ivProfle);

        var tvRecipeType: TextView = view.findViewById(R.id.tvRecipeType)
        tvRecipeType.text = recipeType

        var tvRecipe: TextView = view.findViewById(R.id.tvRecipe)
        tvRecipe.text = recipe

        var tvIngredients: TextView = view.findViewById(R.id.tvIngredients)
        tvIngredients.text = ingredient

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.item1).setVisible(true)
        menu.findItem(R.id.item2).setVisible(true)
        menu.findItem(R.id.item3).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                return redirectHandler.redirectToUpdate(recipeList)
            }

            R.id.item2 -> {
                return redirectHandler.redirectToDuplicate(recipeList)
            }

            R.id.item3 -> {
                deleteRecipeList(ID)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteRecipeList(deleteID:Int) {
        val propertyService = ServiceBuilder.buildService(PropertyService::class.java)
        val requestCall = propertyService.deleteRecipe(ID)

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


}

