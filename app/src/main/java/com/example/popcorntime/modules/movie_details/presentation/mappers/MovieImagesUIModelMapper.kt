package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImagesUIModel

fun MovieImagesDomainModel.toUI(): MovieImagesUIModel {
    return MovieImagesUIModel(
        id = id,
        images = images?.map { it.toUI() },
        posters = posters?.map { it.toUI() }
    )
}