package com.android.example.recipeapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.android.example.recipeapp.RecipeDetails.RecipeDetails
import com.android.example.recipeapp.RecipeDetails.RecipeDuplicate
import com.android.example.recipeapp.RecipeDetails.RecipeUpdate
import com.android.example.recipeapp.models.Recipe
import com.android.example.skbeonpropinvest.RecipeList


class MainActivity : AppCompatActivity(), ClickEventHandler, RedirectEventHandler {
    override fun redirectToUpdate(recipe: Recipe): Boolean {
        val managerUpdate = supportFragmentManager
        val transactionUpdate = managerUpdate.beginTransaction()
        val fragmentListUpdate = RecipeUpdate()
        val argsUpdate = Bundle()
        argsUpdate.putString("NameUpdate", recipe.name)
        argsUpdate.putString("RecipeTypeUpdate", recipe.recipetype)
        argsUpdate.putString("ImageUpdate", recipe.image)
        argsUpdate.putString("IngredientUpdate", recipe.ingredients)
        argsUpdate.putString("RecipeUpdate", recipe.recipe)
        argsUpdate.putInt("IdUpdate", recipe.id)
        fragmentListUpdate.arguments = argsUpdate

        transactionUpdate.replace(R.id.fragment, fragmentListUpdate)
        transactionUpdate.addToBackStack(null)
        transactionUpdate.commit()
        return true
    }

    override fun redirectToRecipeList() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentList = RecipeList()
        transaction.replace(R.id.fragment, fragmentList)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun redirectToDuplicate(recipe: Recipe): Boolean {
        val managerUpdate = supportFragmentManager
        val transactionUpdate = managerUpdate.beginTransaction()
        val fragmentListUpdate = RecipeDuplicate()
        val argsUpdate = Bundle()
        argsUpdate.putString("NameUpdate", recipe.name)
        argsUpdate.putString("RecipeTypeUpdate", recipe.recipetype)
        argsUpdate.putString("ImageUpdate", recipe.image)
        argsUpdate.putString("IngredientUpdate", recipe.ingredients)
        argsUpdate.putString("RecipeUpdate", recipe.recipe)
        argsUpdate.putInt("IdUpdate", recipe.id)
        fragmentListUpdate.arguments = argsUpdate

        transactionUpdate.replace(R.id.fragment, fragmentListUpdate)
        transactionUpdate.addToBackStack(null)
        transactionUpdate.commit()
        return true
    }

    override fun forwardClick(recipe: Recipe) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentList = RecipeDetails()
        val args = Bundle()
        args.putString("Name", recipe.name)
        args.putString("RecipeType", recipe.recipetype)
        args.putString("Image", recipe.image)
        args.putString("Ingredient", recipe.ingredients)
        args.putString("Recipe", recipe.recipe)
        args.putInt("Id", recipe.id)
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
        val fragmentList = RecipeList()

        transaction.replace(R.id.fragment, fragmentList)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return true
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

