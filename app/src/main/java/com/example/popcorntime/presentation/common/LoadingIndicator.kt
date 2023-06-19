package com.example.popcorntime.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingHomeScreen() {
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), contentPadding = PaddingValues(4.dp)) {
        items(12) {
            AnimatedShimmer {
                ShimmerMovieCard(
                    Modifier
                        .padding(4.dp), brush = it
                )
            }
        }
    }
}