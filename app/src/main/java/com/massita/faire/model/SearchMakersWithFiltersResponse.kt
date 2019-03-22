package com.massita.faire.model

import com.google.gson.annotations.SerializedName

data class SearchMakersWithFiltersResponse(
    @SerializedName("products_for_category") var productsForCategory: ProductsForCategory,
    @SerializedName("brands") var brands: List<Brand>,
    @SerializedName("pagination_data") var paginationData: PaginationData
) {

    data class ProductsForCategory(
        @SerializedName("products") var products : List<Product>,
        @SerializedName("category") var category: String
    )

}