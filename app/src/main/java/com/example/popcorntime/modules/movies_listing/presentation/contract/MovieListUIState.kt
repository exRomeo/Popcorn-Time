package com.example.popcorntime.modules.movies_listing.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.example.popcorntime.common.presentation.contract.BaseUIState
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.common.presentation.models.SearchWidgetState
import com.example.popcorntime.common.presentation.models.SortBy
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import kotlinx.coroutines.flow.Flow

data class MovieListUIState(
    override val networkError: MutableState<Boolean> = mutableStateOf(false),
    override val refreshing: MutableState<Boolean> = mutableStateOf(false),
    val searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.Closed),
    val searchTextState: MutableState<String> = mutableStateOf(""),
    val sortingState: MutableState<SortBy> = mutableStateOf(SortBy.Popular),
    val isSearching: MutableState<Boolean> = mutableStateOf(false),
    val language: MutableState<Language> = mutableStateOf(Language.English),
    val moviesListPagingSource: MutableState<Flow<PagingData<MovieUIModel>>?> = mutableStateOf(null),
) : BaseUIState
