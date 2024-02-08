package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.retrofit.response.MovieImagesResponse
import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel

fun MovieImagesResponse.toDomain(): MovieImagesDomainModel {
    return MovieImagesDomainModel(
        id = id,
        images = backDrops?.map { it.toDomain() },
        posters = posters?.map { it.toDomain() }
    )
}