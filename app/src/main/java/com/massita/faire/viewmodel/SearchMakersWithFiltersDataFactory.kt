package com.massita.faire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.massita.faire.datasource.SearchMakersWithFiltersDataSource
import com.massita.faire.model.Brand

class SearchMakersWithFiltersDataFactory : DataSource.Factory<Int, Brand>() {

    var brandLiveData = MutableLiveData<SearchMakersWithFiltersDataSource>()
    private lateinit var searchMakersWithFiltersDataSource: SearchMakersWithFiltersDataSource

    var categoryQuery: String? = null
    var nameQuery: String? = null

    override fun create(): DataSource<Int, Brand> {
        searchMakersWithFiltersDataSource = SearchMakersWithFiltersDataSource(categoryQuery, nameQuery)
        brandLiveData.postValue(searchMakersWithFiltersDataSource)
        return searchMakersWithFiltersDataSource
    }

    fun searchCategory(category: String) {
        this.categoryQuery = category
        searchMakersWithFiltersDataSource.clearPagination()
    }

    fun searchName(name: String) {
        this.nameQuery = name
        searchMakersWithFiltersDataSource.clearPagination()
    }
}