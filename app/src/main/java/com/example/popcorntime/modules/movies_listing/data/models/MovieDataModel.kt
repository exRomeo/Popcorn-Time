package com.example.popcorntime.modules.movies_listing.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDataModel(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("genres")
    val genres: List<GenreDataModel>? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,
    @SerializedName("id")
    val id: Long,
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
    val productionCompanies: List<ProductionCompanyDataModel>? = null,
    @SerializedName("production_country")
    val productionCountries: List<ProductionCountryDataModel>? = null,
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
){
    //TODO must remove this from here
    fun getVoteAverage(): Float = ((voteAverage?.times(10))?.toInt() ?: 0) / 10f
}