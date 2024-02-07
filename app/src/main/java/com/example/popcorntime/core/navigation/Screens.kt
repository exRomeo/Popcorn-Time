package com.example.popcorntime.core.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Details : Screens("details")
}
