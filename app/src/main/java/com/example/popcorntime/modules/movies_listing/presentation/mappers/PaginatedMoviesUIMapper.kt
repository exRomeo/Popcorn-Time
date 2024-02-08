package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MoviesListPagingUIModel

fun MoviesListPagingDomainModel.toUI()
        : MoviesListPagingUIModel = MoviesListPagingUIModel(
    page = page,
    movies = movies.map { it.toUI() },
    totalPages = totalPages,
    totalResults = totalResults
)