package com.massita.faire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.massita.faire.client.Api
import com.massita.faire.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {

    private var products = MutableLiveData<List<Product>>()
    private var brandToken : String? = null

    fun getProducts(token: String?) : LiveData<List<Product>> {
        brandToken = token
        loadProducts()
        return products
    }

    private fun loadProducts() {
        val call = Api.create().getProducts(brandToken)

        call.enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // Show some error
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        products.postValue(it)
                    }
                }
            }
        })
    }

}