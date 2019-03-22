package com.massita.faire.model

import com.google.gson.annotations.SerializedName


// Mapping the useful data for this assignment
data class Product(
    @SerializedName("token") var token: String,
    @SerializedName("version") var version : Long?,
    @SerializedName("brand_token") var brandToken : String?,
    @SerializedName("active") var active : Boolean?,
    @SerializedName("name") var name : String?,
    @SerializedName("short_description") var shortDescription: String?,
    @SerializedName("wholesale_price_cents") var wholesalePriceCents: Long?,
    @SerializedName("retail_price_cents") var retailPriceCents: Long?,
    @SerializedName("images") var images: List<Image>?
)