package com.example.popcorntime.data.datasource

import com.example.popcorntime.data.models.MoviesResponse
import retrofit2.Response

interface IMoviesSource {
    suspend fun getMovies(sortBy: String, language: String, page: UInt): Response<MoviesResponse>
}