package com.android.example.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recipeapp.helper.RecipeAdapter
import com.android.example.recipeapp.models.Recipe
import com.android.example.recipeapp.services.RecipeService
import com.android.example.recipeapp.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response


class RecipeList : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_destiny_list, container, false)

        //Recyclerview initialize and load sample data
        val rvRecipeList = view.findViewById(R.id.recycler_view) as? RecyclerView

        loadRecipeList(rvRecipeList, view)

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.item1).setVisible(false)
        menu.findItem(R.id.item2).setVisible(false)
        menu.findItem(R.id.item3).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }

    fun loadRecipeList(recyclerView: RecyclerView?, view: View) {
        val filter = HashMap<String, String>()
        val recipeService = ServiceBuilder.buildService(RecipeService::class.java)
        val requestCall = recipeService.getRecipeList(filter)

        requestCall.enqueue(object : retrofit2.Callback<List<Recipe>> {

            // If you receive a HTTP Response, then this method is executed
            // Your STATUS Code will decide if your Http Response is a Success or Error
            override fun onResponse(
                call: Call<List<Recipe>>,
                response: Response<List<Recipe>>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200's
                    val retrofitRecipeList = response.body()!!
                    recyclerView?.adapter = RecipeAdapter(retrofitRecipeList)
                    recyclerView?.layoutManager = LinearLayoutManager(view.context)

//                    Spinner initialize and set adapter
                    val spinnerRecipeType = view.findViewById(R.id.spinnerRecipeType) as? Spinner
                    val recipeTypeList = arrayOf("Breakfast", "Dessert", "Dinner", "Lunch", "All")
                    val arrayAdapter =
                        ArrayAdapter(
                            view.context,
                            android.R.layout.simple_spinner_item,
                            recipeTypeList
                        )
                    spinnerRecipeType!!.adapter = arrayAdapter

//                     Set default value for spinner
                    spinnerRecipeType.setSelection(4)
//                    set behaviour of spinner when it is selected
                    spinnerRecipeType.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                var resetRecylerView: Boolean = false
                                val resultList = ArrayList<Recipe>()

                                for (recipes in retrofitRecipeList) {
                                    if (position == 4) {
                                        resetRecylerView = true
                                        break
                                    } else if (recipes.recipetype!!.contains(recipeTypeList[position])) {
                                        resultList.add(recipes)
                                    }
                                }
                                if (resultList.size > 0 && view != null) {
                                    recyclerView?.adapter =
                                        RecipeAdapter(resultList)
                                } else if (resetRecylerView) {
                                    if (retrofitRecipeList != null && view != null) {
                                        recyclerView?.adapter =
                                            RecipeAdapter(retrofitRecipeList)
                                    }
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {

                            }
                        }

                } else if (response.code() == 401) {
                    Toast.makeText(
                        view.context,
                        "Your session has expired. Please Login again.", Toast.LENGTH_LONG
                    ).show()
                } else { // Application-level failure
//                     Your status code is in the range of 300's, 400's and 500's
                    Toast.makeText(view.context, "Failed to retrieve items", Toast.LENGTH_LONG)
                        .show()
                }
            }

            //             Invoked in case of Network Error or Establishing connection with Server
//             or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {

                Toast.makeText(view.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

}

