package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.models.GenreDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.GenreDomainModel

fun GenreDataModel.toDomain(): GenreDomainModel = GenreDomainModel(
    id = id,
    name = name
)