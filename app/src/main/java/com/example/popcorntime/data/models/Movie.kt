package com.example.popcorntime.data.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("adult")
    val adult: Boolean? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @field:SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("original_language")
    val originalLanguage: String? = null,
    @field:SerializedName("original_title")
    val originalTitle: String? = null,
    @field:SerializedName("overview")
    val overview: String? = null,
    @field:SerializedName("popularity")
    val popularity: Double? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("release_date")
    val releaseDate: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("video")
    val video: Boolean? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    @field:SerializedName("vote_count")
    val voteCount: Long? = null
) {
    fun getPosterURL(): String =
        "https://image.tmdb.org/t/p/w500/$posterPath"

}
