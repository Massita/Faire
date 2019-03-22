package com.massita.faire.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Brand(
    @SerializedName("token") var token: String,
    @SerializedName("is_internal") var isInternal: Boolean?,
    @SerializedName("active") var isActive: Boolean?,
    @SerializedName("name") var name: String?,
    @SerializedName("short_description") var shortDescription: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("squared_image") var squaredImage: Image?
) : Parcelable