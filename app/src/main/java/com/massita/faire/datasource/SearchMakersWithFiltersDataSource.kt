package com.massita.faire.datasource

import androidx.paging.ItemKeyedDataSource
import com.massita.faire.client.Api
import com.massita.faire.model.Brand
import com.massita.faire.model.PaginationData
import com.massita.faire.model.SearchMakersWithFiltersRequest
import com.massita.faire.model.SearchMakersWithFiltersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMakersWithFiltersDataSource : ItemKeyedDataSource<Int, Brand>() {

    private val mApi: Api by lazy { Api.create() }

    private var pageNumber = 1
    private var pageSize = 5
    private var categoryQuery: String? = null
    private var nameQuery: String? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Brand>) {
        mApi.searchMarkers(SearchMakersWithFiltersRequest(PaginationData(pageNumber, pageSize, null, null), categoryQuery, nameQuery))
            .enqueue(object : Callback<SearchMakersWithFiltersResponse>{
                override fun onFailure(call: Call<SearchMakersWithFiltersResponse>, t: Throwable) {
                    // TODO: Handle the failure
                }

                override fun onResponse(
                    call: Call<SearchMakersWithFiltersResponse>,
                    response: Response<SearchMakersWithFiltersResponse>
                ) {
                    pageNumber++
                    callback.onResult(response.body()?.brands!!)
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Brand>) {
        mApi.searchMarkers(SearchMakersWithFiltersRequest(PaginationData(pageNumber, pageSize, null, null), categoryQuery, nameQuery))
            .enqueue(object : Callback<SearchMakersWithFiltersResponse>{
                override fun onFailure(call: Call<SearchMakersWithFiltersResponse>, t: Throwable) {
                    // TODO: Handle the failure
                }

                override fun onResponse(
                    call: Call<SearchMakersWithFiltersResponse>,
                    response: Response<SearchMakersWithFiltersResponse>
                ) {
                    pageNumber++
                    callback.onResult(response.body()?.brands!!)
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Brand>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKey(item: Brand): Int {
        return pageNumber
    }

}