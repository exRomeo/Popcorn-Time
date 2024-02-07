package com.example.popcorntime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.popcorntime.core.navigation.MainNavigation
import com.example.popcorntime.ui.theme.PopcornTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopcornTimeTheme {
                MainNavigation(navController = rememberNavController())
            }
        }
    }
}