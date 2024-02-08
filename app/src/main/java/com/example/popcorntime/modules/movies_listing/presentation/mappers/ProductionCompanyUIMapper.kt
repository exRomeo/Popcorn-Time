package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.ProductionCompanyDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.ProductionCompanyUIModel

fun ProductionCompanyDomainModel.toUI()
        : ProductionCompanyUIModel = ProductionCompanyUIModel(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)