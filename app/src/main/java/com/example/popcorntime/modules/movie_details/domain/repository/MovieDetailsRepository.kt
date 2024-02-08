package com.example.popcorntime.modules.movie_details.domain.repository

import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel
import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDomainModel

    suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesDomainModel

}