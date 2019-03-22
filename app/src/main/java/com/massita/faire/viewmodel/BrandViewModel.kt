package com.massita.faire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.massita.faire.model.Brand

class BrandViewModel : ViewModel() {

    var brandList: LiveData<PagedList<Brand>>

    private val searchMakersWithFiltersDataFactory = SearchMakersWithFiltersDataFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        brandList = LivePagedListBuilder<Int, Brand>(searchMakersWithFiltersDataFactory, config).build()
    }

    fun setBrandName(name: String) {
        searchMakersWithFiltersDataFactory.searchName(name)
        brandList.value?.dataSource?.invalidate()
    }

    fun setCategory(category: String) {
        searchMakersWithFiltersDataFactory.searchCategory(category)
        brandList.value?.dataSource?.invalidate()
    }
}