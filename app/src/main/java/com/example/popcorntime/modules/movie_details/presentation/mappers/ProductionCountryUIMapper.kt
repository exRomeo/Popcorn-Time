package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.ProductionCountryDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.ProductionCountryUIModel

fun ProductionCountryDomainModel.toUI()
        : ProductionCountryUIModel = ProductionCountryUIModel(
    iso = iso,
    name = name
)