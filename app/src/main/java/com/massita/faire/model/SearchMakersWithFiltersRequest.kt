package com.massita.faire.model

import com.google.gson.annotations.SerializedName

data class SearchMakersWithFiltersRequest(
    @SerializedName("pagination_data") var paginationData: PaginationData,
    @SerializedName("category") var category: String?,
    @SerializedName("name") var name: String?
)
