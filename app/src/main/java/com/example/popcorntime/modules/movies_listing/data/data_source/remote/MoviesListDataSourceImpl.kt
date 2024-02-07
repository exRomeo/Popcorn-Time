package com.example.popcorntime.modules.movies_listing.data.data_source.remote

import com.example.popcorntime.modules.movies_listing.data.retrofit.response.MoviesResponse
import com.example.popcorntime.modules.movies_listing.data.retrofit.service.MoviesListApi
import javax.inject.Inject

class MoviesListDataSourceImpl @Inject constructor(
    private val api: MoviesListApi,
    private val apiKey: String
) : MoviesListDataSource {
    override suspend fun getMovies(
        sortBy: String,
        language: String,
        page: Int
    ): MoviesResponse {
        return api.getMovies(
            apiKey = apiKey,
            sortBy = sortBy,
            language = language,
            page = page
        )
    }

    override suspend fun movieSearch(
        query: String,
        language: String,
        page: Int
    ): MoviesResponse {
        return api.movieSearch(
            apiKey = apiKey,
            query = query,
            includeAdult = false,
            language = language,
            page = page
        )
    }

}