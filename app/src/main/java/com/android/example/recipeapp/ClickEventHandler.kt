package com.android.example.recipeapp

import com.android.example.recipeapp.models.Recipe

interface ClickEventHandler {
    fun forwardClick(recipe: Recipe)
}