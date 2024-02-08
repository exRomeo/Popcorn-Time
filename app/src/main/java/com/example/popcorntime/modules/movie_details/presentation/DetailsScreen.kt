package com.example.popcorntime.modules.movie_details.presentation

import android.content.pm.ActivityInfo
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.popcorntime.R
import com.example.popcorntime.common.presentation.composables.AnimatedShimmer
import com.example.popcorntime.common.presentation.composables.BackDropImageError
import com.example.popcorntime.common.presentation.composables.Error
import com.example.popcorntime.common.presentation.composables.LoadingDetailsScreenPreview
import com.example.popcorntime.common.presentation.composables.LockScreenOrientation
import com.example.popcorntime.common.presentation.composables.NetworkImage
import com.example.popcorntime.common.presentation.composables.NoNetwork
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.common.presentation.models.UIState
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImageUIModel
import com.example.popcorntime.modules.movies_listing.presentation.models.GenreUIModel
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, movieID: Int) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.details),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
        })
    }) {

        DetailsScreenContent(
            modifier = Modifier.padding(it),
            movieID = movieID
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    movieID: Int
) {
    val viewModel: DetailsViewModel = hiltViewModel()


    viewModel.getMovie(movieID, Language.English)
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        viewModel.getMovie(movieID, Language.English)
        refreshing = false
    }

    val refreshState = rememberPullRefreshState(refreshing, ::refresh)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .pullRefresh(refreshState)
    ) {

        DetailsState(modifier = Modifier, viewModel = viewModel)
        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }

}

@Composable
fun DetailsState(modifier: Modifier = Modifier, viewModel: DetailsViewModel) {
    val imagesState by viewModel.images.collectAsState()
    val state by viewModel.movie.collectAsState()
    when (state) {
        is UIState.Loading -> {
            LoadingDetailsScreenPreview()
        }

        is UIState.NotConnected -> {
            NoNetwork(R.raw.no_internet_connection)
        }

        is UIState.Success<*> -> {

            @Suppress("UNCHECKED_CAST")
            val images: MutableList<MovieImageUIModel> =
                (imagesState as UIState.Success<*>).data as MutableList<MovieImageUIModel>

            val movie: MovieUIModel = (state as UIState.Success<*>).data as MovieUIModel
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ImagesSection(
                    modifier = modifier,
                    images = images
                )

                Spacer(modifier = Modifier.padding(8.dp))

                DetailsSection(modifier = modifier.padding(horizontal = 8.dp), movie = movie)
            }
        }

        is UIState.Failure -> {
            val message = (state as UIState.Failure).message
            Error(message, R.raw.error)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesSection(modifier: Modifier = Modifier, images: List<MovieImageUIModel>) {
    val pagerState = rememberPagerState { images.size }
    Box(modifier = modifier.aspectRatio(1.778f)) {
        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(4.dp),
            beyondBoundsPageCount = 3,
            pageSize = PageSize.Fill,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                pagerState,
                Orientation.Horizontal
            ),
            pageContent = {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    NetworkImage(
                        url = images[it].imageUrl,
                        placeHolder = {
                            AnimatedShimmer { brush ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush)
                                )
                            }
                        },
                        error = { BackDropImageError() },
                        contentDescription = "Movie Images"
                    )
                }
            }
        )
    }
}

@Composable
fun DetailsSection(modifier: Modifier = Modifier, movie: MovieUIModel) {
    Column(modifier = modifier) {
        Text(
            text = movie.title.orEmpty(),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )

        GenreChips(movie.genres.orEmpty())

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            item {
                CircleWithPercentage(
                    percentage = movie.average,
                    stringResource(id = R.string.average)
                )
            }
            item {
                DataItem(
                    title = stringResource(R.string.vote),
                    data = movie.voteCount.toString(),
                    imageVector = Icons.Default.BarChart,
                    contentDescription = stringResource(R.string.vote)
                )
            }
            item {
                DataItem(
                    title = stringResource(R.string.language),
                    data = movie.originalLanguage.orEmpty(),
                    imageVector = Icons.Default.Language,
                    contentDescription = stringResource(R.string.language)
                )
            }
            item {
                DataItem(
                    title = stringResource(R.string.status),
                    data = movie.status.orEmpty(),
                    imageVector = Icons.Default.NewReleases,
                    contentDescription = stringResource(R.string.status)
                )
            }
            item {
                DataItem(
                    title = stringResource(R.string.date),
                    data = movie.releaseDate.orEmpty(),
                    imageVector = Icons.Default.DateRange,
                    contentDescription = stringResource(R.string.date)
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.synopsis),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
        Text(
            text = movie.overview.orEmpty(),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
fun GenreChips(genres: List<GenreUIModel>) {
    LazyRow {
        items(genres) {
            AssistChip(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
                label = { Text(text = it.name.orEmpty()) }
            )
        }
    }
}


@Composable
fun CircleWithPercentage(
    percentage: Float,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = percentage / 10f,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Text(
                text = "$percentage"
            )
        }
        Text(text = text)
    }
}

@Composable
fun DataItem(
    modifier: Modifier = Modifier,
    title: String,
    data: String,
    imageVector: ImageVector,
    contentDescription: String
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
        Text(text = title)
        Text(text = data)

    }
}