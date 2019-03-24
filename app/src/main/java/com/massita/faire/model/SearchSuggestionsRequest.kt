package com.massita.faire.model

import com.google.gson.annotations.SerializedName

data class SearchSuggestionsRequest(
    @SerializedName("query") var query: String?
)