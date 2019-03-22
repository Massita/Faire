package com.massita.faire.model

import com.google.gson.annotations.SerializedName

/**
 * Category data class
 * Mapping only the necessary data for this assignment
 */
data class Category(
    @SerializedName("name") var name: String,
    @SerializedName("sub_categories") var subCategories : List<Category>?,
    @SerializedName("is_product_based") var isProductBased : Boolean?,
    @SerializedName("is_featured") var isFeatured : Boolean?
)