package com.example.popcorntime.modules.movies_listing.presentation.models

data class MovieUIModel(
    val adult: Boolean?,
    val genres: List<GenreUIModel>?,
    val backdropPath: String?,
    val genreIDS: List<Long>?,
    val id: Long,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyUIModel>?,
    val productionCountries: List<ProductionCountryUIModel>?,
    val releaseDate: String?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Long?
) {

    val uniqueId
        get() = System.identityHashCode(this)

    //todo: Move this to a mapper and move the url to the constants class
    val poster
        get() = "https://image.tmdb.org/t/p/w500/$posterPath"

    //todo move this to a mapper
    val average
        get() = ((voteAverage?.times(10))?.toInt() ?: 0) / 10f

}