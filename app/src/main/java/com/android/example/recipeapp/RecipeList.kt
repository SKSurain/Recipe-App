package com.android.example.skbeonpropinvest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recipeapp.R
import com.android.example.recipeapp.data.SampleData
import com.android.example.recipeapp.helper.RecipeAdapter
import com.android.example.recipeapp.models.Recipe
import com.android.example.skbeonpropinvest.models.Property
import com.android.example.skbeonpropinvest.services.PropertyService
import com.smartherd.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

private lateinit var recipeList: List<Recipe>

class PropertyList : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_destiny_list, container, false)

        //Recyclerview initialize and load sample data
        val rvRecipeList = view.findViewById(R.id.recycler_view) as? RecyclerView
        var recipeLists: ArrayList<Recipe> = loadSampleData()
        rvRecipeList?.adapter = RecipeAdapter(recipeLists, view!!.context)
        rvRecipeList?.layoutManager = LinearLayoutManager(this.context)

        //Spinner initialize and set adapter
        val spinnerRecipeType = view!!.findViewById(R.id.spinnerRecipeType) as? Spinner
        val recipeTypeList = arrayOf("Breakfast", "Dessert", "Dinner", "Lunch", "All")
        val arrayAdapter =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, recipeTypeList)
        spinnerRecipeType!!.adapter = arrayAdapter

        // Set default value for spinner
        spinnerRecipeType.setSelection(4)

        //set behaviour of spinner when it is selected
        spinnerRecipeType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var resetRecylerView: Boolean = false
                val resultList = ArrayList<Recipe>()

                for (recipes in recipeLists) {
                    if (position == 4) {
                        resetRecylerView = true
                        break
                    } else if (recipes.recipetype!!.contains(recipeTypeList[position])) {
                        resultList.add(recipes)
                    }
                }
                if (resultList.size > 0 && view != null) {
                    rvRecipeList?.adapter = RecipeAdapter(resultList, view!!.context)
                } else if (resetRecylerView) {
                    if (recipeLists != null && view != null) {
                        rvRecipeList?.adapter = RecipeAdapter(recipeLists, view!!.context)
                    }
                    resetRecylerView = false
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        return view
    }

    fun loadSampleData(): ArrayList<Recipe> {
        var recipeLists = SampleData.recipeLists
        return recipeLists
    }


    fun loadPropertyList(recyclerView: RecyclerView?) {
        val filter = HashMap<String, String>()
        val propertyService = ServiceBuilder.buildService(PropertyService::class.java)
        val requestCall = propertyService.getPropertyList(filter)
        requestCall.enqueue(object : retrofit2.Callback<List<Property>> {

            // If you receive a HTTP Response, then this method is executed
            // Your STATUS Code will decide if your Http Response is a Success or Error
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200's
                    val destinationList = response.body()!!
                    Log.v("RecipeList", "Its working " + destinationList )
//                    recyclerView?.adapter = DestinationAdapter(destinationList)

                } else if (response.code() == 401) {
                    Toast.makeText(
                        view?.context,
                        "Your session has expired. Please Login again.", Toast.LENGTH_LONG
                    ).show()
                } else { // Application-level failure
//                     Your status code is in the range of 300's, 400's and 500's
                    Toast.makeText(view?.context, "Failed to retrieve items", Toast.LENGTH_LONG)
                        .show()
                }
            }

            // Invoked in case of Network Error or Establishing connection with Server
            // or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<List<Property>>, t: Throwable) {

                Toast.makeText(view?.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

}

