package com.example.popcorntime.modules.movies_listing.domain.models

data class MovieDomainModel(
    val adult: Boolean?,
    val genres: List<GenreDomainModel>?,
    val backdropPath: String?,
    val genreIDS: List<Long>?,
    val id: Long,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyDomainModel>?,
    val productionCountries: List<ProductionCountryDomainModel>?,
    val releaseDate: String?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Long?
)