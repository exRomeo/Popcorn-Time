package com.example.popcorntime.modules.movies_listing.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductionCountryDataModel(
    @SerializedName("iso_3166_1")
    val iso: String? = null,
    @SerializedName("name")
    val name: String? = null
)

