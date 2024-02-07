package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.GenreDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.GenreUIModel

fun GenreDomainModel.toUI()
        : GenreUIModel = GenreUIModel(
    id = id,
    name = name
)