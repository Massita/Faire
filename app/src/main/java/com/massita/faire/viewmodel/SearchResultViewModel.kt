package com.massita.faire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.massita.faire.client.Api
import com.massita.faire.model.Brand
import com.massita.faire.model.SearchSuggestionsRequest
import com.massita.faire.model.SearchSuggestionsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultViewModel : ViewModel() {

    private var brands = MutableLiveData<List<Brand>>()

    fun getBrands() : LiveData<List<Brand>> {
        return brands
    }

    fun loadSuggestions(query: String) {
        val call = Api.create()
            .getSuggestions(SearchSuggestionsRequest(query))

        call.enqueue(object : Callback<SearchSuggestionsResponse> {
            override fun onFailure(call: Call<SearchSuggestionsResponse>, t: Throwable) {
                // TODO: implement error
            }

            override fun onResponse(
                call: Call<SearchSuggestionsResponse>,
                response: Response<SearchSuggestionsResponse>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        brands.postValue(it.suggestedBrands)
                    }
                }
            }

        })
    }
}