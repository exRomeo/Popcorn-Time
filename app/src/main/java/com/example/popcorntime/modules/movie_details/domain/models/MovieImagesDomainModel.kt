package com.example.popcorntime.modules.movie_details.domain.models

data class MovieImagesDomainModel(
    val id: Long?,
    val images: List<MovieImageDomainModel>?,
    val posters: List<MovieImageDomainModel>?
)
