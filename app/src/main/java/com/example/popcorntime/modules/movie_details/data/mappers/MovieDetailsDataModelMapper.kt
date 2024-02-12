package com.example.popcorntime.modules.movie_details.data.mappers

import com.example.popcorntime.modules.movie_details.data.models.MovieDetailsDataModel
import com.example.popcorntime.modules.movie_details.domain.models.MovieDetailsDomainModel

fun MovieDetailsDataModel.toDomain(): MovieDetailsDomainModel = MovieDetailsDomainModel(
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
    video = video,
    belongsToCollection = belongsToCollection?.toDomain(),
    budget = budget,
    homepage = homepage,
    imdbID = imdbID,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.toDomain() }
)