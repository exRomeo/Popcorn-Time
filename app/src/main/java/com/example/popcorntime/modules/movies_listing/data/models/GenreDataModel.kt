package com.example.popcorntime.modules.movies_listing.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenreDataModel(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null
)
