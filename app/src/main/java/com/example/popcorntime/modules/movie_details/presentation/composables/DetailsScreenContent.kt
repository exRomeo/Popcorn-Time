package com.example.popcorntime.modules.movie_details.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImageUIModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onRefreshClicked: () -> Unit,
    refreshingState: State<Boolean>,
    movie: State<MovieUIModel?>,
    images: State<List<MovieImageUIModel>?>
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshingState.value,
        onRefresh = onRefreshClicked
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            DetailsScreenHeader(onBackClicked = onBackClicked)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .pullRefresh(pullRefreshState)
        ) {

            Column {
                if (images.value?.isNotEmpty() == true)
                    ImagesSection(images = images)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                    }

                    item {
                        GenreItem(genres = movie.value?.genres.orEmpty())
                    }

                    item {
                        MovieInfoSection(movie = movie)
                    }

                    item {
                        MovieOverviewSection(overview = movie.value?.overview.orEmpty())
                    }
                }
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshingState.value,
                state = pullRefreshState
            )
        }
    }
}