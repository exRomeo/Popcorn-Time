package com.example.popcorntime.old_needs_sorting.data.datasource

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.data.models.BackDropsResponse

interface IMoviesSource {
    suspend fun getMovie(
        movieID: Int,
        language: String
    ): MovieDataModel

    suspend fun getImages(
        movieID: Int,
        language: String
    ): BackDropsResponse

}