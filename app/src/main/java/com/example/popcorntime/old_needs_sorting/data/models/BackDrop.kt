package com.example.popcorntime.old_needs_sorting.data.models

import com.google.gson.annotations.SerializedName

data class BackDropsResponse(
    @field:SerializedName("backdrops")
    val backdrops: MutableList<Backdrop>? = null,
    @field:SerializedName("id")
    val id: Long? = null,
    @field:SerializedName("posters")
    val posters: List<Backdrop>? = null
)

data class Backdrop(
    @field:SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,
    @field:SerializedName("height")
    val height: Long? = null,
    @field:SerializedName("file_path")
    val filePath: String? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    @field:SerializedName("vote_count")
    val voteCount: Long? = null,
    @field:SerializedName("width")
    val width: Long? = null
){
    fun getImageURL():String =
        "https://image.tmdb.org/t/p/original/$filePath"
}