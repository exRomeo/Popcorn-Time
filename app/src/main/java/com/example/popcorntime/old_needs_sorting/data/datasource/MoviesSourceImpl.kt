package com.example.popcorntime.old_needs_sorting.data.datasource

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.core.service.MovieAPI
import com.example.popcorntime.old_needs_sorting.data.models.BackDropsResponse
import javax.inject.Inject

class MoviesSourceImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val apiKey: String
) : IMoviesSource {

    override suspend fun getMovie(
        movieID: Int,
        language: String
    ): MovieDataModel =
        movieAPI.getMovie(
            apiKey = apiKey,
            movieID = movieID,
            language = language
        )

    override suspend fun getImages(
        movieID: Int,
        language: String
    ): BackDropsResponse =
        movieAPI.getImages(
            apiKey = apiKey,
            movieID = movieID,
            language = language
        )
}