package com.example.popcorntime.modules.movies_listing.data.mappers

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel

fun MovieDataModel.toDomain(): MovieDomainModel {
    return MovieDomainModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        adult = adult,
        backdropPath = backdropPath,
        genreIDS = genreIDS,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        video = video
    )
}