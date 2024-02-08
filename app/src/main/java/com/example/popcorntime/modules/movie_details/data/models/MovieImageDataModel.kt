package com.example.popcorntime.modules.movie_details.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieImageDataModel(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,
    @SerializedName("height")
    val height: Long? = null,
    @SerializedName("file_path")
    val filePath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null,
    @SerializedName("width")
    val width: Long? = null
)