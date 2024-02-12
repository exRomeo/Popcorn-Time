package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.GenreDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.GenreUIModel

fun GenreDomainModel.toUI()
        : GenreUIModel = GenreUIModel(
    id = id,
    name = name
)