package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.SpokenLanguageDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.SpokenLanguageUIModel

fun SpokenLanguageDomainModel.toUI(): SpokenLanguageUIModel =
    SpokenLanguageUIModel(
        englishName = englishName,
        iso639_1 = iso639_1,
        name = name,
    )