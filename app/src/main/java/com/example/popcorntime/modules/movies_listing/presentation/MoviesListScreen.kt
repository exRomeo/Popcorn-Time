package com.example.popcorntime.modules.movies_listing.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.popcorntime.common.presentation.composables.MovieCard
import com.example.popcorntime.common.presentation.composables.UIEventsObserver
import com.example.popcorntime.common.presentation.models.SortBy
import com.example.popcorntime.common.presentation.navigation.Screens
import com.example.popcorntime.modules.movies_listing.presentation.composables.MoviesListScreenContent
import com.example.popcorntime.modules.movies_listing.presentation.contract.MovieListUIEvent
import com.example.popcorntime.modules.movies_listing.presentation.contract.MovieListUserEvent
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesListScreen(navController: NavHostController) {
    val viewModel: MoviesListViewModel = hiltViewModel()
    val uiState = remember { viewModel.uiState }
    val searchWidgetState = remember { uiState.searchWidgetState }
    val searchTextState = remember { uiState.searchTextState }
    val pagingSource = remember { uiState.moviesListPagingSource }

    val onChangeSorting: (SortBy) -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.ChangeSorting(it)
            )
        }
    }

    val onTextChanged: (String) -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.SearchTextChanged(it)
            )
        }
    }

    val onCloseClicked: () -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.CloseClicked
            )
        }
    }

    val onSearchClicked: (String) -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.MovieSearch(it)
            )
        }
    }

    val onSearchTriggered: () -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.SearchMode
            )
        }
    }

    val onRefresh: () -> Unit = remember {
        {
            viewModel.setUserEvent(
                MovieListUserEvent.Refresh
            )
        }
    }

    MoviesListScreenContent(
        searchWidgetState = searchWidgetState,
        searchTextState = searchTextState,
        pagingSource = pagingSource,
        onChangeSorting = onChangeSorting,
        onTextChanged = onTextChanged,
        onCloseClicked = onCloseClicked,
        onSearchClicked = onSearchClicked,
        onSearchTriggered = onSearchTriggered,
        refreshingState = uiState.refreshing,
        onRefresh = onRefresh
    )


    UIEventsObserver(flow = viewModel.uiEvents) { event ->
        when (event) {
            is MovieListUIEvent.OpenMovieDetails -> {
                navController.navigate(
                    Screens.Details(event.movieId),
                    builder = {
                        launchSingleTop = true
                    },
                )
            }
        }
    }
}

@Composable
fun MovieGrid(
    modifier: Modifier = Modifier,
    pagingSource: Flow<PagingData<MovieUIModel>>
) {
    val movies = pagingSource.collectAsLazyPagingItems()
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.itemCount, key = movies.itemKey { it.uniqueId }) {
            val movie = remember(it) { movies[it] }
            movie?.let {
                MovieCard(
                    Modifier.clickable(onClick = { movie.onClick?.invoke() }), movie = movie
                )
            }
        }
        /*        item {
                    when (movies.loadState.append) {
                        is LoadState.Error -> {
                            MovieCardPlaceholder(
                                Modifier.padding(4.dp),
                                stringResource(id = R.string.not_connected),
                                animation = R.raw.no_internet_connection
                            )
                        }

                        LoadState.Loading -> {
                            AnimatedShimmer {
                                MovieCardPlaceholder(
                                    Modifier.padding(4.dp), brush = it
                                )
                            }
                        }

                        is LoadState.NotLoading -> Unit
                    }
                }*/
    }

}