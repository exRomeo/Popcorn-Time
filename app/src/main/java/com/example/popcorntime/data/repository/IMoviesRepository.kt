package com.example.popcorntime.data.repository

import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse
import com.example.popcorntime.data.models.SortBy
import retrofit2.Response

interface IMoviesRepository {
    suspend fun getMovies(
        sortBy: SortBy,
        language: Language,
        page: Int
    ): Response<MoviesResponse>

    suspend fun getMovie(
        movieID: Int,
        language: Language
    ): Response<Movie>

    suspend fun getImages(
        movieID: Int,
        language: Language
    ): Response<BackDropsResponse>
}