package com.example.popcorntime.modules.movies_listing.data.data_source.remote

import com.example.popcorntime.modules.movies_listing.data.retrofit.response.MoviesResponse

interface MoviesListDataSource {

    suspend fun getMovies(
        sortBy: String,
        language: String, page: Int
    ): MoviesResponse

    suspend fun movieSearch(
        query: String,
        language: String,
        page: Int
    ): MoviesResponse
}