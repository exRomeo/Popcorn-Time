package com.example.popcorntime.modules.movie_details.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BelongsToCollectionDataModel(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
)