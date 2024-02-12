package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.ProductionCompanyDataModel
import com.example.popcorntime.modules.movie_details.domain.models.ProductionCompanyDomainModel

fun ProductionCompanyDataModel.toDomain(): ProductionCompanyDomainModel =
    ProductionCompanyDomainModel(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )