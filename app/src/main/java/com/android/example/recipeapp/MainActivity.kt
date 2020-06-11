package com.android.example.recipeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.android.example.recipeapp.RecipeDetails.RecipeDetails
import com.android.example.recipeapp.models.Recipe
import com.android.example.skbeonpropinvest.PropertyList


class MainActivity : AppCompatActivity(), ClickEventHandler {
    override fun forwardClick(recipe: Recipe) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentList = RecipeDetails()
        val args = Bundle()
        args.putString("Name", recipe.name)
        args.putString("RecipeType", recipe.recipetype)
        args.putString("Image", recipe.image)
        args.putStringArrayList("Ingredient", recipe.ingredients)
        args.putString("Recipe", recipe.recipe)
        fragmentList.arguments = args

        transaction.replace(R.id.fragment, fragmentList)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentList = PropertyList()

        transaction.replace(R.id.fragment, fragmentList)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {

        val fm: FragmentManager = supportFragmentManager
        var backStackCount = fm.getBackStackEntryCount()
        if (backStackCount > 1) {
            Log.i("MainActivity", "popping backstack")
            fm.popBackStack()
        } else {
            finish()
        }
    }
}

