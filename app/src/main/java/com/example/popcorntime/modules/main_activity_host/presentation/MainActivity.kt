package com.example.popcorntime.modules.main_activity_host.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.popcorntime.common.presentation.ui.theme.PopcornTimeTheme
import com.example.popcorntime.common.presentation.navigation.MainNavigation
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