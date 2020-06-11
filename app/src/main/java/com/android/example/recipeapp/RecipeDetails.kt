package com.android.example.recipeapp.RecipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recipeapp.R
import com.android.example.recipeapp.helper.IngredientListAdapter
import com.android.example.recipeapp.models.Recipe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private lateinit var recipeList: List<Recipe>
private var name :String =""
private var ingredient : ArrayList<String> = ArrayList()
private var recipeType : String = ""
private var recipe : String = ""
private var image : String = ""

class RecipeDetails : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if(arguments != null){
            name = arguments!!.get("Name").toString()
            ingredient = arguments!!.getStringArrayList("Ingredient") as ArrayList<String>
            recipeType = arguments!!.get("RecipeType").toString()
            recipe = arguments!!.get("Recipe").toString()
            image = arguments!!.get("Image").toString()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.recipe_details, container, false)

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

        //Recyclerview initialize and load sample data
        val rvIngredientList = view.findViewById(R.id.rvIngredientList) as? RecyclerView
        var ingredientLists: ArrayList<String> = ingredient
        rvIngredientList?.adapter = IngredientListAdapter(ingredientLists, view!!.context)
        rvIngredientList?.layoutManager = LinearLayoutManager(this.context)

        return view
    }

}

