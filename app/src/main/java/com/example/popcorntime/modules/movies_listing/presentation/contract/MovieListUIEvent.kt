package com.example.popcorntime.modules.movies_listing.presentation.contract

import com.example.popcorntime.common.presentation.contract.BaseUIEvent

sealed class MovieListUIEvent : BaseUIEvent {
    data class OpenMovieDetails(val movieId: Long) : MovieListUIEvent()
}