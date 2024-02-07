package com.example.popcorntime.modules.movies_listing.data.repository

import com.example.popcorntime.modules.movies_listing.data.data_source.remote.MoviesListDataSource
import com.example.popcorntime.modules.movies_listing.data.mappers.toDomain
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel
import com.example.popcorntime.modules.movies_listing.domain.repository.MoviesListRepository
import javax.inject.Inject

class MoviesListRepositoryImpl @Inject constructor(
    private val dataSource: MoviesListDataSource
) : MoviesListRepository {

    override suspend fun getMovies(
        sortBy: String,
        language: String,
        page: Int
    ): List<MovieDataModel> {
        return dataSource.getMovies(sortBy, language, page).movies
    }

    override suspend fun getPaginatedMovies(
        sortBy: String,
        language: String,
        page: Int
    ): MoviesListPagingDomainModel {
        return dataSource.getMovies(sortBy, language, page).toDomain()
    }

    override suspend fun searchMovies(
        query: String,
        language: String,
        page: Int
    ): List<MovieDataModel> {
        return dataSource.movieSearch(query, language, page).movies
    }

    override suspend fun searchPaginatedMovies(
        query: String,
        language: String,
        page: Int
    ): MoviesListPagingDomainModel {
        return dataSource.movieSearch(query, language, page).toDomain()
    }
}