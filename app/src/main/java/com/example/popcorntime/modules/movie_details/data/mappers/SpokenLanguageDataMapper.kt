package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.SpokenLanguageDataModel
import com.example.popcorntime.modules.movie_details.domain.models.SpokenLanguageDomainModel

fun SpokenLanguageDataModel.toDomain(): SpokenLanguageDomainModel =
    SpokenLanguageDomainModel(
        englishName = englishName,
        iso639_1 = iso639_1,
        name = name,
    )