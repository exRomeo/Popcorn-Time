package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

fun MovieDomainModel.toUI(): MovieUIModel = MovieUIModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genres = genres?.map { it.toUI() },
    productionCompanies = productionCompanies?.map { it.toUI() },
    productionCountries = productionCountries?.map { it.toUI() },
    status = status,
    tagline = tagline,
    popularity = popularity,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    adult = adult,
    video = video,
    genreIDS = genreIDS
)