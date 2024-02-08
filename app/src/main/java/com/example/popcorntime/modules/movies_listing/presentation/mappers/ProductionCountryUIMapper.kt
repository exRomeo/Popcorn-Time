package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.ProductionCountryDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.ProductionCountryUIModel

fun ProductionCountryDomainModel.toUI()
        : ProductionCountryUIModel = ProductionCountryUIModel(
    iso = iso,
    name = name
)