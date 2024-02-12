package com.example.popcorntime.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(val route: String) {
    open operator fun invoke(): String = route

    data object Home : Screens("home")
    data object Details : Screens("details") {
        const val movieIdNavArg = "movieID"
        val arguments = listOf(
            navArgument(movieIdNavArg) {
                type = NavType.IntType
            }
        )

        override operator fun invoke(): String = "$route/{$movieIdNavArg}"
        operator fun invoke(movieId: Long): String = "$route/$movieId"

        @Composable
        inline fun GetArguments(
            backStackEntry: NavBackStackEntry,
            content: @Composable (movieId: Int) -> Unit
        ) {
            val movieId = backStackEntry.arguments?.getInt(movieIdNavArg)
            movieId?.let { content(movieId) }
        }

    }
}
