package com.example.popcorntime.modules.movie_details.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.popcorntime.common.presentation.contract.BaseUIState
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImageUIModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

data class MovieDetailsUIState(
    override val networkError: MutableState<Boolean> = mutableStateOf(false),
    override val loading: MutableState<Boolean> = mutableStateOf(false),
    val movie: MutableState<MovieUIModel?> = mutableStateOf(null),
    val images: MutableState<List<MovieImageUIModel>?> = mutableStateOf(null)
) : BaseUIState
