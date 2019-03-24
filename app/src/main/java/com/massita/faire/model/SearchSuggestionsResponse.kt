package com.massita.faire.model

import com.google.gson.annotations.SerializedName

data class SearchSuggestionsResponse(
    @SerializedName("suggested_brands") var suggestedBrands: List<Brand>
)