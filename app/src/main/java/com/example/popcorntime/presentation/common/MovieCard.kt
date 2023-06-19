package com.example.popcorntime.presentation.common

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.ui.theme.PopcornTimeTheme

@Composable
fun MovieCard(modifier: Modifier = Modifier, titleHeight: Dp = 40.dp, movie: Movie) {
    Card(
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = movie.getPosterURL(),
                placeHolder = { PosterImagePlaceholder() },
                error = { PosterImageError() },
                contentDescription = "Movie Image"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(titleHeight)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        text = movie.title ?: "N/A",
                        style = TextStyle(textAlign = TextAlign.Center),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun PreviewMovieCard() {
    Scaffold() {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            item {
                MovieCard(
                    movie = Movie(
                        adult = false,
                        backdropPath = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
                        genreIDS = listOf(18, 80),
                        id = 238,
                        originalLanguage = "en",
                        originalTitle = "The Godfather",
                        overview =
                        "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                        popularity = 112.803,
                        posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                        releaseDate = "1972-03-14",
                        title = "The Godfather",
                        video = false,
                        voteAverage = 8.7,
                        voteCount = 18097

                    )
                )
            }
        }
    }
}


@Composable
fun MovieCardShimmer(modifier: Modifier = Modifier, brush: Brush) {
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
                        MovieCardShimmer(brush = it)
                    }
                }
            }
        }
    }
}
