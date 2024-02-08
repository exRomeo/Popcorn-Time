package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.MovieImageDataModel
import com.example.popcorntime.modules.movie_details.domain.models.MovieImageDomainModel

fun MovieImageDataModel.toDomain(): MovieImageDomainModel {
    return MovieImageDomainModel(
        aspectRatio = aspectRatio,
        width = width,
        height = height,
        filePath = filePath,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}