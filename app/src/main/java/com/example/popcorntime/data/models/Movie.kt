package com.example.popcorntime.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Movie(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_country")
    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null
) {
    val uniqueId: Int
        get() = /*id ?:*/ System.identityHashCode(this)
    fun getPosterURL(): String =
        "https://image.tmdb.org/t/p/w500/$posterPath"

    fun getVoteAverage(): Float = ((voteAverage?.times(10))?.toInt() ?: 0) / 10f

}

data class ProductionCompany(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Genre(
    val id: Long? = null,
    val name: String? = null
)