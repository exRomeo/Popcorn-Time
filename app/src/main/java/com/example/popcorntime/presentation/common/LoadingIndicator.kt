package com.example.popcorntime.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingHomeScreen() {
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), contentPadding = PaddingValues(4.dp)) {
        items(12) {
            AnimatedShimmer {
                MovieCardShimmer(
                    Modifier
                        .padding(4.dp), brush = it
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoadingHomeScreenPreview(){
    LoadingHomeScreen()
}
@Composable
fun PosterImagePlaceholder(){
    AnimatedShimmer {
        Box(
            Modifier
                .aspectRatio(2 / 3f)
                .background(it)
        )
    }
}
