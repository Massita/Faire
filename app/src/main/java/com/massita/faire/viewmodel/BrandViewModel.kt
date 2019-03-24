package com.massita.faire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.massita.faire.client.Api
import com.massita.faire.model.Brand
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandViewModel : ViewModel() {

    var brand = MutableLiveData<Brand>()

    fun getBrand() : LiveData<Brand> {
        return brand
    }

    fun loadBrand(brandToken: String) {
        val call = Api.create()
            .getBrand(brandToken)

        call.enqueue(object : Callback<Brand> {
            override fun onFailure(call: Call<Brand>, t: Throwable) {
                // TODO: On error
            }

            override fun onResponse(call: Call<Brand>, response: Response<Brand>) {
                response.body()?.let {
                    brand.postValue(it)
                }
            }

        })
    }
}