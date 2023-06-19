package com.example.popcorntime.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.SortBy
import com.example.popcorntime.presentation.common.Error
import com.example.popcorntime.presentation.common.HomeTopAppBar
import com.example.popcorntime.presentation.common.LoadingHomeScreen
import com.example.popcorntime.presentation.common.MovieCard
import com.example.popcorntime.presentation.common.NoNetwork

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = { HomeTopAppBar() }) {
        HomeScreenContent(
            modifier =
            Modifier.padding(it),
            navController = navController
        )
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            moviesRepository = (LocalContext.current.applicationContext as PopcornTimeApplication).moviesRepository,
            connectionUtil = (LocalContext.current.applicationContext as PopcornTimeApplication).connectionUtil
        )
    )
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.state.collectAsState()
        when (state) {
            is UIState.Loading -> {
                LoadingHomeScreen()
            }

            is UIState.NotConnected -> {
                NoNetwork()
            }

            is UIState.Success -> {
                MovieGrid(navController = navController, viewModel = viewModel)
            }

            is UIState.Failure -> {
                val message = (state as UIState.Failure).message
                Error(message)
            }
        }

    }
}

@Composable
fun MovieGrid(navController: NavHostController, viewModel: HomeViewModel) {
    val moviesList: List<Movie> by viewModel.movies.collectAsState()
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), contentPadding = PaddingValues(4.dp)) {
        items(items = moviesList) {
            MovieCard(
                Modifier
                    .padding(4.dp)
                    .clickable {/*TODO: Navigate to Details Screen*/ },
                movie = it
            )

            if (moviesList.indexOf(it) >= moviesList.size - 1)
                viewModel.loadNextPage(sortBy = SortBy.Popular, language = Language.English)

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
                HomeViewModel(
                    moviesRepository = (LocalContext.current.applicationContext as PopcornTimeApplication).moviesRepository,
                    connectionUtil = (LocalContext.current.applicationContext as PopcornTimeApplication).connectionUtil
                )
            )
        }
    }
}