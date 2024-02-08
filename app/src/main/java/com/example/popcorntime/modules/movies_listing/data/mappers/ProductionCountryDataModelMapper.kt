package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.models.ProductionCountryDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.ProductionCountryDomainModel

fun ProductionCountryDataModel.toDomain(): ProductionCountryDomainModel =
    ProductionCountryDomainModel(
        iso = iso,
        name = name
    )