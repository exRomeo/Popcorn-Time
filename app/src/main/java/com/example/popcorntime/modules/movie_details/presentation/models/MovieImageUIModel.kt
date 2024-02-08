package com.example.popcorntime.modules.movie_details.presentation.models

data class MovieImageUIModel(
    val aspectRatio: Double?,
    val width: Long?,
    val height: Long?,
    val filePath: String?,
    val voteAverage: Double?,
    val voteCount: Long?,
) {
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/original$filePath"
}
