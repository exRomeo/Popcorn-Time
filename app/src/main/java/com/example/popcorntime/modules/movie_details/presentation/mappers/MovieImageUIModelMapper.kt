package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.MovieImageDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImageUIModel

fun MovieImageDomainModel.toUI(): MovieImageUIModel {
    return MovieImageUIModel(
        aspectRatio = aspectRatio,
        width = width,
        height = height,
        filePath = filePath,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}