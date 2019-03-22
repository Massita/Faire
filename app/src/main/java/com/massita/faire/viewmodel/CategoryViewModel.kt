package com.massita.faire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.massita.faire.model.Category

class CategoryViewModel : ViewModel() {

    private var category = MutableLiveData<Category>()
    private var subCategory = MutableLiveData<Category>()


    fun setCategory(category: Category) {
        this.category.postValue(category)
    }

    fun getCategory() : MutableLiveData<Category> {
        return category
    }

    fun setSubcategory(category: Category) {
        this.subCategory.postValue(category)
    }

    fun getSubCategory() : MutableLiveData<Category> {
        return subCategory
    }

}