package com.example.popcorntime.modules.movie_details.presentation.models

data class MovieImagesUIModel(
    val id: Long?,
    val images: List<MovieImageUIModel>?,
    val posters: List<MovieImageUIModel>?
)