package com.example.popcorntime.modules.movies_listing.domain.repository

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel

interface MoviesListRepository {
    suspend fun getMovies(
        sortBy: String,
        language: String,
        page: Int
    ): List<MovieDataModel>

    suspend fun getPaginatedMovies(
        sortBy: String,
        language: String,
        page: Int
    ): MoviesListPagingDomainModel

    suspend fun searchMovies(
        query: String,
        language: String,
        page: Int
    ): List<MovieDataModel>

    suspend fun searchPaginatedMovies(
        query: String,
        language: String,
        page: Int
    ): MoviesListPagingDomainModel
}