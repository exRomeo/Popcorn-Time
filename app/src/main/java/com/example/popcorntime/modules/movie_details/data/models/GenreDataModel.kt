package com.example.popcorntime.modules.movie_details.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenreDataModel(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null
)
