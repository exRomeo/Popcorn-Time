package com.example.popcorntime.modules.movies_listing.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.popcorntime.R
import com.example.popcorntime.common.navigation.Screens
import com.example.popcorntime.common.presentation.composables.AnimatedShimmer
import com.example.popcorntime.common.presentation.composables.Error
import com.example.popcorntime.common.presentation.composables.LoadingHomeScreen
import com.example.popcorntime.common.presentation.composables.MainAppBar
import com.example.popcorntime.common.presentation.composables.MovieCard
import com.example.popcorntime.common.presentation.composables.MovieCardPlaceholder
import com.example.popcorntime.common.presentation.composables.NoNetwork
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.old_needs_sorting.core.state.SearchWidgetState
import com.example.popcorntime.old_needs_sorting.core.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun MoviesListScreen(navController: NavHostController) {
    val viewModel: MoviesListViewModel = hiltViewModel()

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState.collectAsState()

    Scaffold(topBar = {
        MainAppBar(
            searchWidgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = { viewModel.updateSearchTextState(newValue = it) },
            onCloseClicked = {
                viewModel.updateSearchTextState(newValue = "")
                viewModel.updateSearchWidgetState(newValue = SearchWidgetState.Closed)
            },
            onSearchClicked = { viewModel.movieSearch(it) },
            onSearchTriggered = { viewModel.updateSearchWidgetState(SearchWidgetState.Opened) },
            onFilterClicked = {
                viewModel.filter = it
                viewModel.getMovies()
            }
        )
    }) {

        HomeScreenContent(
            modifier =
            Modifier.padding(it),
            viewModel = viewModel,
            navController = navController
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel,
    navController: NavHostController
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.state.collectAsState()
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            viewModel.refresh()
        }

        val refreshState = rememberPullRefreshState(refreshing, ::refresh)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
        ) {
            when (state) {
                is UIState.Loading -> {
                    refreshing = true
                    LoadingHomeScreen()
                }

                is UIState.NotConnected -> {
                    NoNetwork(R.raw.no_internet_connection)
                }

                is UIState.Success<*> -> {
                    refreshing = false
                    @Suppress("UNCHECKED_CAST")
                    val movies: Flow<PagingData<MovieUIModel>> =
                        (state as UIState.Success<*>).data as Flow<PagingData<MovieUIModel>>
                    MovieGrid(
                        navController = navController,
                        list = movies
                    )
                }

                is UIState.Failure -> {
                    val message = (state as UIState.Failure).message
                    Error(message, R.raw.error)
                }
            }
            PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
fun MovieGrid(
    navController: NavHostController,
    list: Flow<PagingData<MovieUIModel>>
) {
    val movies = list.collectAsLazyPagingItems()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.itemCount, key = movies.itemKey { it.uniqueId }) {
            MovieCard(
                Modifier
                    .clickable {
                        navController.navigate(
                            Screens.Details.route + "/${movies[it]!!.id}",
                            builder = {
                                launchSingleTop = true
                            })
                    },
                movie = movies[it]!!
            )
        }
        item {
            when (movies.loadState.append) {
                is LoadState.Error -> {
                    MovieCardPlaceholder(
                        Modifier
                            .padding(4.dp),
                        stringResource(id = R.string.not_connected),
                        animation = R.raw.no_internet_connection
                    )
                }

                LoadState.Loading -> {
                    AnimatedShimmer {
                        MovieCardPlaceholder(
                            Modifier
                                .padding(4.dp), brush = it
                        )
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}