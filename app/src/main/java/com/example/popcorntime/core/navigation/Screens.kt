package com.example.popcorntime.core.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Details : Screens("details")
}
