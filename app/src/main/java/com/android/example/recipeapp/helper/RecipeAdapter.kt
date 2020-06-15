package com.android.example.recipeapp.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recipeapp.R
import com.android.example.recipeapp.RecipeDetails
import com.android.example.recipeapp.RedirectEventHandler
import com.android.example.recipeapp.models.Recipe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private lateinit var view: View

class RecipeAdapter(private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(parent.context).inflate(R.layout.rv_recipe_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.recipe = recipeList[position]
        holder.tvName.text = recipeList[position].name
        holder.tvRecipeType.text = recipeList[position].recipetype

        val redirectEventHandler: RedirectEventHandler = view.context as RedirectEventHandler

        val imageView = recipeList[position].image

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(view).load(imageView).apply(options).into(holder.ivObject);

        holder.itemView.setOnClickListener {
            redirectEventHandler.redirectFragment(recipeList[position], RecipeDetails())
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var recipe: Recipe? = null
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        var ivObject: ImageView = itemView.findViewById(R.id.ivIcons)
        var tvRecipeType: TextView = itemView.findViewById(R.id.tvRecipeType)


        override fun toString(): String {
            return """${super.toString()} '${tvName.text}'"""
        }
    }
}
