package com.example.popcorntime.modules.movie_details.data.repository

import com.example.popcorntime.modules.movie_details.data.mappers.toDomain
import com.example.popcorntime.modules.movie_details.data.remote.MovieDetailsDataSource
import com.example.popcorntime.modules.movie_details.domain.models.MovieDetailsDomainModel
import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel
import com.example.popcorntime.modules.movie_details.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieDetailsDataSource: MovieDetailsDataSource,
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDetailsDomainModel {
        return movieDetailsDataSource
            .getMovieDetails(
                movieID = movieID,
                language = language
            ).toDomain()
    }

    override suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesDomainModel {
        return movieDetailsDataSource
            .getImages(
                movieID = movieID,
                language = language
            ).toDomain()
    }
}