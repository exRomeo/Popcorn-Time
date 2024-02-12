package com.example.popcorntime.modules.movies_listing.domain.models

data class MoviesListPagingDomainModel(
    val page: Int?,
    val movies: List<MovieDomainModel>?,
    val totalPages: Int?,
    val totalResults: Int?
)
