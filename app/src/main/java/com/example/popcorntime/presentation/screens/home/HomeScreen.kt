package com.example.popcorntime.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.core.navigation.Screens
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.SortBy
import com.example.popcorntime.presentation.common.Error
import com.example.popcorntime.presentation.common.HomeTopAppBar
import com.example.popcorntime.presentation.common.LoadingHomeScreen
import com.example.popcorntime.presentation.common.MovieCard
import com.example.popcorntime.presentation.common.NoNetwork
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            moviesRepository = (LocalContext.current.applicationContext as PopcornTimeApplication).moviesRepository,
            connectionUtil = (LocalContext.current.applicationContext as PopcornTimeApplication).connectionUtil
        )
    )
    Scaffold(topBar = { HomeTopAppBar() }) {

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
    viewModel: HomeViewModel,
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
            refreshing = true
            viewModel.refresh(SortBy.Popular, Language.English)
            refreshing = false
        }

        val refreshState = rememberPullRefreshState(refreshing, ::refresh)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
        ) {
            if (!refreshing)
                when (state) {
                    is UIState.Loading -> {
                        LoadingHomeScreen()
                    }

                    is UIState.NotConnected -> {
                        NoNetwork()
                    }

                    is UIState.Success<*> -> {
                        val movies: List<Movie> = (state as UIState.Success<*>).data as List<Movie>
                        MovieGrid(
                            navController = navController,
                            list = movies,
                            viewModel = viewModel
                        )
                    }

                    is UIState.Failure -> {
                        val message = (state as UIState.Failure).message
                        Error(message)
                    }
                }
            PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
        }


    }
}

@Composable
fun MovieGrid(navController: NavHostController, list: List<Movie>, viewModel: HomeViewModel) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(items = list) {
            MovieCard(
                Modifier
                    .padding(4.dp)
                    .clickable { navController.navigate(Screens.Details.route + "/${it.id}") },
                movie = it
            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(topBar = { HomeTopAppBar() }) {
        Column(Modifier.padding(it)) {
            MovieGrid(
                rememberNavController(),
                listOf(),
                HomeViewModel(
                    moviesRepository = (LocalContext.current.applicationContext as PopcornTimeApplication).moviesRepository,
                    connectionUtil = (LocalContext.current.applicationContext as PopcornTimeApplication).connectionUtil
                )
            )
        }
    }
}