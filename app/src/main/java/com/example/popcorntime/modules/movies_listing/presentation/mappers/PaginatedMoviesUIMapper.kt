package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MoviesListPagingUIModel

fun MoviesListPagingDomainModel.toUI(onClick: ((MovieUIModel) -> Unit)?)
        : MoviesListPagingUIModel = MoviesListPagingUIModel(
    page = page,
    movies = movies?.map { it.toUI(onClick = { onClick?.invoke(it.toUI(null)) }) },
    totalPages = totalPages,
    totalResults = totalResults
)