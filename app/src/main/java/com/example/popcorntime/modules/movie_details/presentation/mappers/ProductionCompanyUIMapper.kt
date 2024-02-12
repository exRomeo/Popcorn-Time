package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.ProductionCompanyDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.ProductionCompanyUIModel

fun ProductionCompanyDomainModel.toUI()
        : ProductionCompanyUIModel = ProductionCompanyUIModel(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)