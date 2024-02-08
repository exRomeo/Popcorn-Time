package com.example.popcorntime.modules.movie_details.domain.models

data class MovieImageDomainModel(
    val aspectRatio: Double?,
    val width: Long?,
    val height: Long?,
    val filePath: String?,
    val voteAverage: Double?,
    val voteCount: Long?,
)