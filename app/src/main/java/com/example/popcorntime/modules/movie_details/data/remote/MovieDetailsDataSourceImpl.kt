package com.example.popcorntime.modules.movie_details.data.remote

import com.example.popcorntime.modules.movie_details.data.retrofit.response.MovieImagesResponse
import com.example.popcorntime.modules.movie_details.data.retrofit.service.MovieDetailsApi
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import javax.inject.Inject

class MovieDetailsDataSourceImpl @Inject constructor(
    private val apiKey: String,
    private val movieDetailsApi: MovieDetailsApi
) : MovieDetailsDataSource {
    override suspend fun getMovieDetails(
        movieID: Int,
        language: String
    ): MovieDataModel =
        movieDetailsApi.getMovieDetails(
            apiKey = apiKey,
            movieID = movieID,
            language = language
        )

    override suspend fun getImages(
        movieID: Int,
        language: String
    ): MovieImagesResponse =
        movieDetailsApi.getImages(
            apiKey = apiKey,
            movieID = movieID,
            language = language
        )
}