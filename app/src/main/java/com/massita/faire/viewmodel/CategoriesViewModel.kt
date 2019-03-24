package com.massita.faire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.massita.faire.client.Api
import com.massita.faire.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {

    private var categories = MutableLiveData<List<Category>>()

    fun getCategories() : LiveData<List<Category>> {
        listCategories()
        return categories
    }


    private fun listCategories() {
        val call = Api.create().getCategories()

        call.enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                // TODO: Do something on fail
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        categories.postValue(it)
                        return
                    }
                }
            }

        })
    }


}