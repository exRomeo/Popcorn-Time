package com.example.popcorntime.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Error(message: String) {
    Text(text = message)
}

@Composable
fun NoNetwork() {
    Text(text = "NO INTERNET\n CHECK CONNECTION")
}
