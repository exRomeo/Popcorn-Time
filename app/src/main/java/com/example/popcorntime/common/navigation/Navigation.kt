package com.example.popcorntime.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.popcorntime.old_needs_sorting.presentation.screens.details.DetailsScreen
import com.example.popcorntime.modules.movies_listing.presentation.MoviesListScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route
        ) {

            MoviesListScreen(navController = navController)
        }
        composable(
            route = Screens.Details.route + "/{movieID}",
            arguments = listOf(navArgument("movieID") {
                type = NavType.IntType
            }
            )
        ) {
            it.arguments?.getInt("movieID")
                ?.let { it1 -> DetailsScreen(navController = navController, movieID = it1) }
        }
    }
}