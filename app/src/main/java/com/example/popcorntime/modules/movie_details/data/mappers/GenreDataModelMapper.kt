package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.GenreDataModel
import com.example.popcorntime.modules.movie_details.domain.models.GenreDomainModel

fun GenreDataModel.toDomain(): GenreDomainModel = GenreDomainModel(
    id = id,
    name = name
)