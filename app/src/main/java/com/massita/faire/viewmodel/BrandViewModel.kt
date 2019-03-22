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
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        brandList = LivePagedListBuilder<Int, Brand>(searchMakersWithFiltersDataFactory, config).build()
    }
}