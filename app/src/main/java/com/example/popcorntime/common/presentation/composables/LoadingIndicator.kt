package com.example.popcorntime.common.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingHomeScreen() {
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), contentPadding = PaddingValues(4.dp)) {
        items(12) {
            AnimatedShimmer {
                MovieCardPlaceholder(
                    Modifier
                        .padding(4.dp), brush = it
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoadingHomeScreenPreview() {
    LoadingHomeScreen()
}

@Composable
fun PosterImagePlaceholder() {
    AnimatedShimmer {
        Box(
            Modifier
                .aspectRatio(2 / 3f)
                .background(it)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoadingDetailsScreenPreview() {
    Scaffold {
        LoadingDetailScreen(Modifier.padding(it))
    }
}

@Composable
fun LoadingDetailScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        AnimatedShimmer {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(1.778f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(it)
            )

            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(25.dp)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Row(
                Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(
                    Modifier
                        .padding(start = 8.dp)
                        .width(70.dp)
                        .height(35.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(it)
                )
                Spacer(
                    Modifier
                        .padding(start = 8.dp)
                        .height(35.dp)
                        .width(125.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(it)
                )
                Spacer(
                    Modifier
                        .padding(start = 8.dp)
                        .height(35.dp)
                        .width(95.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(it)
                )
            }
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.95f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.85f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.95f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.85f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
            Spacer(
                Modifier
                    .padding(start = 8.dp, top = 16.dp)
                    .height(15.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(25.dp))
                    .background(it)
            )
        }
    }
}