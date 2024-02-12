package com.example.popcorntime.modules.movie_details.presentation.mappers

import com.example.popcorntime.modules.movie_details.domain.models.MovieDetailsDomainModel
import com.example.popcorntime.modules.movie_details.presentation.models.MovieDetailsUIModel

fun MovieDetailsDomainModel.toUI(): MovieDetailsUIModel = MovieDetailsUIModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    genres = genres?.map { it.toUI() },
    genreIDS = genreIDS,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    productionCompanies = productionCompanies?.map { it.toUI() },
    productionCountries = productionCountries?.map { it.toUI() },
    status = status,
    tagline = tagline,
    video = video,
    belongsToCollection = belongsToCollection?.toUI(),
    budget = budget,
    homepage = homepage,
    imdbID = imdbID,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.toUI() },
    averageRating = ((voteAverage?.times(10))?.toInt() ?: 0) / 10f
)