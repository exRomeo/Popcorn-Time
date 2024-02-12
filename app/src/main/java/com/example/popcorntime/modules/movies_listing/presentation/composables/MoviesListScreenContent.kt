package com.example.popcorntime.modules.movies_listing.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import com.example.popcorntime.common.presentation.composables.PullRefreshBox
import com.example.popcorntime.common.presentation.composables.ScreenHeaderWithSearch
import com.example.popcorntime.common.presentation.models.SearchWidgetState
import com.example.popcorntime.common.presentation.models.SortBy
import com.example.popcorntime.modules.movies_listing.presentation.MovieGrid
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesListScreenContent(
    modifier: Modifier = Modifier,
    searchWidgetState: State<SearchWidgetState>,
    searchTextState: State<String>,
    pagingSource: State<Flow<PagingData<MovieUIModel>>?>,
    onChangeSorting: (SortBy) -> Unit,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    refreshingState: State<Boolean>,
    onRefresh: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ScreenHeaderWithSearch(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = onTextChanged,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,
                onSearchTriggered = onSearchTriggered,
                onChangeSorting = onChangeSorting
            )
        }) {
        PullRefreshBox(
            modifier = Modifier.padding(it),
            refreshingState = refreshingState,
            onRefresh = onRefresh
        ) {
            pagingSource.value?.let { source ->
                MovieGrid(
                    modifier = Modifier.fillMaxSize(),
                    pagingSource = source
                )
            }
        }
    }
}