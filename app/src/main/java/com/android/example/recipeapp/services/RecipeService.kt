package com.android.example.recipeapp.services

import com.android.example.recipeapp.models.Recipe


import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("recipe")
    fun getRecipeList(@QueryMap filter: HashMap<String, String>): Call<List<Recipe>>

    @FormUrlEncoded
    @PUT("recipe/{id}")
    fun updateRecipe(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("ingredients") ingredients: String,
        @Field("recipe") recipe: String
    ): Call<Recipe>

    @DELETE("recipe/{id}")
    fun deleteRecipe(@Path("id") id: Int): Call<Unit>

    @POST("recipe")
    fun addRecipe(@Body newRecipe: Recipe): Call<Recipe>
}