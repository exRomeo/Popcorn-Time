package com.example.popcorntime.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.popcorntime.modules.movie_details.presentation.DetailsScreen
import com.example.popcorntime.modules.movies_listing.presentation.MoviesListScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home()) {
        composable(
            route = Screens.Home()
        ) {
            MoviesListScreen(navController = navController)
        }
        composable(
            route = Screens.Details(),
            arguments = Screens.Details.arguments
        ) {
            Screens.Details.GetArguments(it) { movieID ->
                DetailsScreen(
                    navController = navController,
                    movieID = movieID
                )
            }
        }
    }
}