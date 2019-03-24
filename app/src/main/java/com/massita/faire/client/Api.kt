package com.massita.faire.client

import android.util.Log
import com.massita.faire.model.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("/api/category/new")
    fun getCategories() : Call<List<Category>>

    @POST( "/api/search/makers-with-filters")
    fun searchMarkers(@Body requestBody: SearchMakersWithFiltersRequest) : Call<SearchMakersWithFiltersResponse>

    @GET("/api/brand/{brand_token}/products")
    fun getProducts(@Path("brand_token") brandToken: String?) : Call<List<Product>>

    @POST("/api/search/suggestions")
    fun getSuggestions(@Body requestBody: SearchSuggestionsRequest) : Call<SearchSuggestionsResponse>

    @GET("/api/brand/{brand_token}")
    fun getBrand(@Path("brand_token") brandToken: String?) : Call<Brand>

    companion object {
        private const val BASE_URL = "https://www.faire.com/"

        fun create() : Api = create(HttpUrl.parse(BASE_URL)!!)

        private fun create(baseUrl: HttpUrl): Api {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BODY

            // Creating the OkHttp Client
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

}