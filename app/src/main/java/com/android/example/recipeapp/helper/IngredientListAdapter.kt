package com.android.example.recipeapp.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recipeapp.ClickEventHandler
import com.android.example.recipeapp.R

private lateinit var view: View
class IngredientListAdapter(private val recipeList: ArrayList<String>, context: Context) :
    RecyclerView.Adapter<IngredientListAdapter.ViewHolder>() {
    private val clickHandler: ClickEventHandler = context as ClickEventHandler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(parent.context).inflate(R.layout.array_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.recipe.text = "- " + recipeList[position]

    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recipe: TextView = itemView.findViewById(R.id.tvName)
    }
}
