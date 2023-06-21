package com.example.popcorntime.data.datasource

import com.example.popcorntime.BuildConfig
import com.example.popcorntime.core.helpers.MovieAPI
import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse
import retrofit2.Response

class MoviesSource(private val movieAPI: MovieAPI) : IMoviesSource {

    override suspend fun getMovies(
        sortBy: String,
        language: String,
        page: UInt
    ): Response<MoviesResponse> =
        movieAPI.getMovies(
            apiKey = BuildConfig.APIKEY,
            sortBy = sortBy,
            language = language,
            page = page
        )

    override suspend fun getMovie(
        movieID: Int,
        language: String
    ): Response<Movie> =
        movieAPI.getMovie(
            apiKey = BuildConfig.APIKEY,
            movieID = movieID,
            language = language
        )

    override suspend fun getImages(
        movieID: Int,
        language: String
    ): Response<BackDropsResponse> =
        movieAPI.getImages(
            apiKey = BuildConfig.APIKEY,
            movieID = movieID,
            language = language
        )

}