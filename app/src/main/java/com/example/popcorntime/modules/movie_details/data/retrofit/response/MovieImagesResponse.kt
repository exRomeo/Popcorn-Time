package com.example.popcorntime.modules.movie_details.data.retrofit.response

import androidx.annotation.Keep
import com.example.popcorntime.modules.movie_details.data.models.MovieImageDataModel
import com.google.gson.annotations.SerializedName

@Keep
data class MovieImagesResponse(
    @SerializedName("backdrops")
    val backDrops: MutableList<MovieImageDataModel>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("posters")
    val posters: List<MovieImageDataModel>? = null
)