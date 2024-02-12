package com.example.popcorntime.modules.movie_details.domain.repository

import com.example.popcorntime.modules.movie_details.domain.models.MovieDetailsDomainModel
import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDetailsDomainModel

    suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesDomainModel

}