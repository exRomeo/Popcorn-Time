package com.example.popcorntime.data.datasource

import com.example.popcorntime.core.helpers.MovieAPI
import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse

class MoviesSource(private val movieAPI: MovieAPI, private val apiKey: String) : IMoviesSource {

    override suspend fun getMovies(
        sortBy: String,
        language: String,
        page: UInt
    ): MoviesResponse =
        movieAPI.getMovies(
            apiKey = apiKey,
            sortBy = sortBy,
            language = language,
            page = page
        )

    override suspend fun getMovie(
        movieID: Int,
        language: String
    ): Movie =
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

    override suspend fun movieSearch(
        query: String,
        language: String,
        page: UInt
    ): MoviesResponse =
        movieAPI.movieSearch(
            apiKey = apiKey,
            query = query,
            include_adult = false,
            language = language,
            page = page
        )

}