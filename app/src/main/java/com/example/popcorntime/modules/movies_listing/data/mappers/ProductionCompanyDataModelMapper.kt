package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.models.ProductionCompanyDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.ProductionCompanyDomainModel

fun ProductionCompanyDataModel.toDomain(): ProductionCompanyDomainModel =
    ProductionCompanyDomainModel(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )