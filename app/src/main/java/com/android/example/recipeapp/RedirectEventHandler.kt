package com.android.example.recipeapp

import androidx.fragment.app.Fragment
import com.android.example.recipeapp.models.Recipe

interface RedirectEventHandler {
    fun redirectToRecipeList()
    fun redirectFragment(recipe: Recipe, fragment: Fragment)
}