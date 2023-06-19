package com.example.popcorntime.data.repository

import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.MoviesResponse
import com.example.popcorntime.data.models.SortBy
import retrofit2.Response

interface IMoviesRepository {
    suspend fun getMovies(
        sortBy: SortBy,
        language: Language,
        page: Int
    ): Response<MoviesResponse>
}