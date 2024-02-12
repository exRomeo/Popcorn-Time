package com.example.popcorntime.modules.movies_listing.presentation.mappers

import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

fun MovieDomainModel.toUI(onClick: (() -> Unit)?): MovieUIModel {
    return MovieUIModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500/$posterPath",
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        adult = adult,
        backdropPath = backdropPath,
        genreIDS = genreIDS,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        video = video,
        onClick = onClick
    )
}