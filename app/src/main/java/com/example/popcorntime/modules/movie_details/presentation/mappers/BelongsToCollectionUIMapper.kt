package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.BelongsToCollectionDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.BelongsToCollectionUIModel

fun BelongsToCollectionDomainModel.toUI(): BelongsToCollectionUIModel =
    BelongsToCollectionUIModel(
        backdropPath = backdropPath,
        id = id,
        name = name,
        posterPath = posterPath
    )