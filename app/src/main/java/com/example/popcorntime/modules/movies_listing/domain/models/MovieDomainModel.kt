package com.example.popcorntime.modules.movies_listing.domain.models

data class MovieDomainModel(
    val id: Long?,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIDS: List<Long>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Long?
)