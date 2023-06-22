package com.example.popcorntime.data.repository

import com.example.popcorntime.data.datasource.IMoviesSource
import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse
import com.example.popcorntime.data.models.SortBy

class MoviesRepository(private val moviesSource: IMoviesSource) : IMoviesRepository {
    override suspend fun getMovies(
        sortBy: SortBy,
        language: Language,
        page: Int
    ): MoviesResponse =
        moviesSource.getMovies(
            sortBy = sortBy.value,
            language = language.value,
            page = page.toUInt()
        )

    override suspend fun getMovie(
        movieID: Int,
        language: Language
    ): Movie =
        moviesSource.getMovie(
            movieID = movieID,
            language = language.value
        )

    override suspend fun getImages(
        movieID: Int,
        language: Language
    ): BackDropsResponse =
        moviesSource.getImages(
            movieID = movieID,
            language = language.value
        )

}