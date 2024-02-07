package com.example.popcorntime.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.popcorntime.presentation.screens.details.DetailsScreen
import com.example.popcorntime.presentation.screens.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route
        ) {

            HomeScreen(navController = navController)
        }
        composable(
            route = Screens.Details.route + "/{movieID}",
            arguments = listOf(
                navArgument("movieID") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(200)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(200)
                )
            }
        ) {
            it.arguments?.getInt("movieID")
                ?.let { it1 -> DetailsScreen(navController = navController, movieID = it1) }
        }
    }
}