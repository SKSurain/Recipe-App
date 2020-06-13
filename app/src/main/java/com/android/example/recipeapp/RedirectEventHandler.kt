package com.android.example.recipeapp

import com.android.example.recipeapp.models.Recipe

interface RedirectEventHandler {
    fun redirectToUpdate(recipe: Recipe): Boolean
    fun redirectToRecipeList()
    fun redirectToDuplicate(recipe: Recipe): Boolean
}