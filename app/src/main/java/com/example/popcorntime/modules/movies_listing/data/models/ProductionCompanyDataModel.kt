package com.example.popcorntime.modules.movies_listing.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductionCompanyDataModel(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)