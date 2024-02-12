package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.ProductionCountryDataModel
import com.example.popcorntime.modules.movie_details.domain.models.ProductionCountryDomainModel

fun ProductionCountryDataModel.toDomain(): ProductionCountryDomainModel =
    ProductionCountryDomainModel(
        iso = iso,
        name = name
    )