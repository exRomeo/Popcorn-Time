package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.retrofit.response.MoviesResponse
import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel

fun MoviesResponse.toDomain(): MoviesListPagingDomainModel {
    return MoviesListPagingDomainModel(
        page = page,
        movies = movies?.map { it.toDomain() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}