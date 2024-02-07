package com.example.popcorntime.old_needs_sorting.data.repository

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.core.state.Language
import com.example.popcorntime.old_needs_sorting.data.datasource.IMoviesSource
import com.example.popcorntime.old_needs_sorting.data.models.BackDropsResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesSource: IMoviesSource
) : IMoviesRepository {


    override suspend fun getMovie(
        movieID: Int,
        language: Language
    ): MovieDataModel =
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