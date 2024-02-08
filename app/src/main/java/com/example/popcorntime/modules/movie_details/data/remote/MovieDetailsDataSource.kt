package com.example.popcorntime.modules.movie_details.data.remote

import com.example.popcorntime.modules.movie_details.data.retrofit.response.MovieImagesResponse
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDataModel

    suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesResponse
}