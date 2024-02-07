package com.example.popcorntime.old_needs_sorting.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BackDropsResponse(
    @SerializedName("backdrops")
    val backdrops: MutableList<Backdrop>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("posters")
    val posters: List<Backdrop>? = null
)

@Keep
data class Backdrop(
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
){
    fun getImageURL():String =
        "https://image.tmdb.org/t/p/original/$filePath"
}