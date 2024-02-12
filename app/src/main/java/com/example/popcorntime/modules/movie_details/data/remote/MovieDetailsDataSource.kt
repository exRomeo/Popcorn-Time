package com.example.popcorntime.modules.movie_details.data.remote

import com.example.popcorntime.modules.movie_details.data.models.MovieDetailsDataModel
import com.example.popcorntime.modules.movie_details.data.retrofit.response.MovieImagesResponse

interface MovieDetailsDataSource {
    suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDetailsDataModel

    suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesResponse
}