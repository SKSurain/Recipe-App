package com.android.example.recipeapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.example.recipeapp.models.Recipe


class MainActivity : AppCompatActivity(), RedirectEventHandler {

    override fun redirectToRecipeList() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentList = RecipeList()
        transaction.replace(R.id.fragment, fragmentList)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun redirectFragment(recipe: Recipe, fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val args = Bundle()
        args.putString("Name", recipe.name)
        args.putString("RecipeType", recipe.recipetype)
        args.putString("Image", recipe.image)
        args.putString("Ingredient", recipe.ingredients)
        args.putString("Recipe", recipe.recipe)
        args.putInt("Id", recipe.id)
        fragment.arguments = args

        transaction.replace(R.id.fragment, fragment)
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
            fm.popBackStack()
        } else {
            finish()
        }
    }
}

