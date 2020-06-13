package com.android.example.skbeonpropinvest.services

import com.android.example.recipeapp.models.Recipe
import com.android.example.recipeapp.models.Recipe2
import com.android.example.skbeonpropinvest.models.Property
import com.android.example.skbeonpropinvest.models.User

import retrofit2.Call
import retrofit2.http.*

interface PropertyService {

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

    @GET("property")
    fun getPropertyList(@QueryMap filter: HashMap<String, String>): Call<List<Property>>

    @GET("property/{id}")
    fun getProperty(@Path("id") id: Int): Call<Property>

    @POST("property")
    fun addProperty(@Body newProperty: Property): Call<Property>

    @FormUrlEncoded
    @PUT("property/{id}")
    fun updateProperty(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") desc: String,
        @Field("country") country: String
    ): Call<Property>

    @DELETE("property/{id}")
    fun deleteProperty(@Path("id") id: Int): Call<Unit>
}