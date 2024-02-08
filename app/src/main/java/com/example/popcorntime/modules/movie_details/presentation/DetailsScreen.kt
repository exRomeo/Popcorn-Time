package com.example.popcorntime.modules.movie_details.presentation

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.popcorntime.common.presentation.composables.LockScreenOrientation
import com.example.popcorntime.common.presentation.composables.UIEventsObserver
import com.example.popcorntime.modules.movie_details.di.DetailsViewModelFactory
import com.example.popcorntime.modules.movie_details.presentation.composables.DetailsScreenContent
import com.example.popcorntime.modules.movie_details.presentation.contract.MovieDetailsUIEvent
import com.example.popcorntime.modules.movie_details.presentation.contract.MovieDetailsUserEvent

@Composable
fun DetailsScreen(navController: NavHostController, movieID: Int) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val viewModel: DetailsViewModel =
        hiltViewModel(
            creationCallback = { vmf: DetailsViewModelFactory ->
                vmf.create(movieID)
            }
        )

    val refreshingState = remember { viewModel.uiState.loading }
    val movie = remember { viewModel.uiState.movie }
    val images = remember { viewModel.uiState.images }

    val onBackClicked = remember {
        {
            viewModel.setUserEvent(MovieDetailsUserEvent.BackClicked)
        }
    }

    val refreshScreen = remember {
        {
            viewModel.setUserEvent(MovieDetailsUserEvent.RefreshScreen)
        }
    }

    DetailsScreenContent(
        onBackClicked = onBackClicked,
        onRefreshClicked = refreshScreen,
        refreshingState = refreshingState,
        movie = movie,
        images = images
    )

    UIEventsObserver(viewModel.uiEvents) { event ->
        when (event) {
            MovieDetailsUIEvent.NavigateBack -> {
                navController.navigateUp()
            }
        }
    }
}