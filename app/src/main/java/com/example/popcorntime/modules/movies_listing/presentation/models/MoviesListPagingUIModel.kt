package com.example.popcorntime.modules.movies_listing.presentation.models

data class MoviesListPagingUIModel(
    val page: Int?,
    val totalPages: Int?,
    val totalResults: Int?,
    val movies: List<MovieUIModel>?
)