package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel

fun MovieDataModel.toDomain() : MovieDomainModel = MovieDomainModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    genres = genres?.map { it.toDomain() },
    genreIDS = genreIDS,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    productionCompanies = productionCompanies?.map { it.toDomain() },
    productionCountries = productionCountries?.map { it.toDomain() },
    status = status,
    tagline = tagline,
    video = video
)