package com.example.popcorntime.old_needs_sorting.data.repository

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.core.state.Language
import com.example.popcorntime.old_needs_sorting.data.models.BackDropsResponse

interface IMoviesRepository {

    suspend fun getMovie(
        movieID: Int,
        language: Language
    ): MovieDataModel

    suspend fun getImages(
        movieID: Int,
        language: Language
    ): BackDropsResponse

}