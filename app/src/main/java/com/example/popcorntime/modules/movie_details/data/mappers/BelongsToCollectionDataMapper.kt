package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.BelongsToCollectionDataModel
import com.example.popcorntime.modules.movie_details.domain.models.BelongsToCollectionDomainModel

fun BelongsToCollectionDataModel.toDomain(): BelongsToCollectionDomainModel =
    BelongsToCollectionDomainModel(
        backdropPath = backdropPath,
        id = id,
        name = name,
        posterPath = posterPath
    )