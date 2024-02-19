package com.example.popcorntime.common.presentation.composables

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.RawRes
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.popcorntime.R
import com.example.popcorntime.common.presentation.ui.theme.PopcornTimeTheme
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

@Composable
fun MovieCard(modifier: Modifier = Modifier, titleHeight: Dp = 40.dp, movie: MovieUIModel) {
    Card(
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                image = movie.posterPath,
                placeHolder = R.drawable.error_placeholder,
                error = R.drawable.error_placeholder,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(titleHeight),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = movie.title ?: "N/A",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun MovieCardPlaceholder(modifier: Modifier = Modifier, brush: Brush) {
    Card(modifier) {
        Box(
            modifier = Modifier
                .aspectRatio(2 / 3f)
                .background(brush)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth(fraction = 0.9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
            )
        }
    }
}

@Composable
fun MovieCardPlaceholder(
    modifier: Modifier = Modifier,
    message: String = "",
    @RawRes animation: Int = R.raw.loading_fan
) {
    Card(modifier) {
        Box(
            modifier = Modifier
                .aspectRatio(2 / 3f)
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    animation
                )
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = modifier
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = message,
                    style = TextStyle(textAlign = TextAlign.Center),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun ShimmeringMovieCrdPreview() {
    PopcornTimeTheme {
        Scaffold {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                item {
                    AnimatedShimmer {
                        MovieCardPlaceholder(brush = it)
                    }
                }
            }
        }
    }
}
