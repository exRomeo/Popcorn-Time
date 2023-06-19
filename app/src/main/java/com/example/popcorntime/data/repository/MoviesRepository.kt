package com.example.popcorntime.data.repository

import com.example.popcorntime.data.datasource.IMoviesSource
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.MoviesResponse
import com.example.popcorntime.data.models.SortBy
import retrofit2.Response

class MoviesRepository(private val moviesSource: IMoviesSource) : IMoviesRepository {
    override suspend fun getMovies(
        sortBy: SortBy,
        language: Language,
        page: Int
    ): Response<MoviesResponse> =
        moviesSource.getMovies(sortBy = sortBy.value, language = language.value, page = page.toUInt())
}