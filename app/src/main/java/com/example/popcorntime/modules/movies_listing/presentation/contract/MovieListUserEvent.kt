package com.example.popcorntime.modules.movies_listing.presentation.contract

import com.example.popcorntime.common.presentation.contract.BaseUserEvent
import com.example.popcorntime.common.presentation.models.SortBy

sealed class MovieListUserEvent : BaseUserEvent {
    data object Refresh : MovieListUserEvent()
    data class OpenMovieDetails(val movieId: Long?) : MovieListUserEvent()
    data class ChangeSorting(val sortBy: SortBy) : MovieListUserEvent()
    data class SearchTextChanged(val query: String) : MovieListUserEvent()
    data class MovieSearch(val query: String) : MovieListUserEvent()
    data object SearchMode : MovieListUserEvent()
    data object CloseClicked : MovieListUserEvent()
}