package com.example.popcorntime.presentation.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.presentation.common.AnimatedShimmer
import com.example.popcorntime.presentation.common.Error
import com.example.popcorntime.presentation.common.NetworkImage
import com.example.popcorntime.presentation.common.NoNetwork

@Composable
fun DetailsScreen(navController: NavHostController, movieID: Int) {
    Scaffold() {
        DetailsScreenContent(
            modifier = Modifier.padding(it),
            navController = navController,
            movieID = movieID
        )
    }
}

@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    movieID: Int
) {
    val viewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory(
            moviesRepository = (LocalContext.current.applicationContext as PopcornTimeApplication).moviesRepository,
            connectionUtil = (LocalContext.current.applicationContext as PopcornTimeApplication).connectionUtil
        )
    )

    viewModel.getMovie(movieID, Language.English)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.state.collectAsState()
        when (state) {
            is UIState.Loading -> {

            }

            is UIState.NotConnected -> {
                NoNetwork()
            }

            is UIState.Success -> {
                val movie = (state as UIState.Success).data
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
                    val configuration = LocalConfiguration.current
                    Text(text = movie?.title ?: "N/A")
                    MovieImages(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((configuration.screenWidthDp / 1.8).dp),
                        viewModel = viewModel
                    )

                    LazyColumn {
                        item {
                            Text(text = movie?.overview ?: "N/A")
                        }
                    }

                }
            }

            is UIState.Failure -> {
                val message = (state as UIState.Failure).message
                Error(message)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieImages(modifier: Modifier = Modifier, viewModel: DetailsViewModel) {
    val images by viewModel.images.collectAsState()
    HorizontalPager(
        modifier = modifier,
        state = rememberPagerState { images.size },
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(4.dp),
        beyondBoundsPageCount = 3,
        pageSize = PageSize.Fill,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = {
            Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
                NetworkImage(
                    url = images[it].getImageURL(),
                    placeHolder = {
                        AnimatedShimmer { brush ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(brush)
                            )
                        }
                    },
                    error = { /*TODO*/ },
                    contentDescription = "Movie Images"
                )
            }
        }
    )
}

@Composable
@Preview(showSystemUi = true)
fun DetailsScreenPreview() {

}