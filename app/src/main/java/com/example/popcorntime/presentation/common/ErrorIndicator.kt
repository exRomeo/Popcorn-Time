package com.example.popcorntime.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.popcorntime.R

@Composable
fun Error(message: String) {
    Text(text = message)
}

@Composable
fun NoNetwork() {
    LazyColumn() {
        item {
            Text(text = "NO INTERNET\n CHECK CONNECTION")
        }
    }
}


@Composable
fun PosterImageError() {
    Box(
        Modifier
            .aspectRatio(2 / 3f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_placeholder),
            contentDescription = "error"
        )
    }
}