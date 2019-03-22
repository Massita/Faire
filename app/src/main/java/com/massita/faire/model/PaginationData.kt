package com.massita.faire.model

import com.google.gson.annotations.SerializedName

data class PaginationData(
    @SerializedName("page_number") var pageNumber: Int?,
    @SerializedName("page_size") var pageSize: Int?,
    @SerializedName("page_count") var pageCount: Int?,
    @SerializedName("total_results") var totalResults: Int?
)