package com.example.popcorntime.data.datasource

import com.example.popcorntime.BuildConfig
import com.example.popcorntime.core.helpers.MovieAPI
import com.example.popcorntime.data.models.MoviesResponse
import retrofit2.Response

class MoviesSource(private val movieAPI: MovieAPI):IMoviesSource {

    override suspend fun getMovies(sortBy: String, language: String, page: UInt): Response<MoviesResponse> =
        movieAPI.getMovies(
            apiKey = BuildConfig.APIKEY,
            sortBy = sortBy,
            language = language,
            page = page
        )

}