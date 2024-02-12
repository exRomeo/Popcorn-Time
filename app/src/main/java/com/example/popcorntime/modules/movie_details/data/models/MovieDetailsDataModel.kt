package com.example.popcorntime.modules.movie_details.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetailsDataModel(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>? = null,
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
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDataModel? = null,
    @SerializedName("budget")
    val budget: Long? = null,
    @SerializedName("genres")
    val genres: List<GenreDataModel>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("imdb_id")
    val imdbID: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDataModel>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDataModel>? = null,
    @SerializedName("revenue")
    val revenue: Long? = null,
    @SerializedName("runtime")
    val runtime: Long? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDataModel>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
)