package com.android.example.recipeapp.models

data class Recipe(
    var id: Int = 0,
    var name: String? = null,
    var recipe: String? = null,
    var ingredients: String? = null,
    var recipetype: String? = null,
    var image: String? = null
)
