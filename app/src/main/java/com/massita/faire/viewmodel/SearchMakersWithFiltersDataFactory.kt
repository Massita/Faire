package com.massita.faire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.massita.faire.datasource.SearchMakersWithFiltersDataSource
import com.massita.faire.model.Brand

class SearchMakersWithFiltersDataFactory : DataSource.Factory<Int, Brand>() {

    private var brandLiveData = MutableLiveData<SearchMakersWithFiltersDataSource>()

    override fun create(): DataSource<Int, Brand> {
        val searchMakersWithFiltersDataSource = SearchMakersWithFiltersDataSource()
        brandLiveData.postValue(searchMakersWithFiltersDataSource)
        return searchMakersWithFiltersDataSource
    }

}