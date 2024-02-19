package com.example.popcorntime.modules.movie_details.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.popcorntime.R
import com.example.popcorntime.common.presentation.composables.NetworkImage
import com.example.popcorntime.modules.movie_details.presentation.models.MovieImageUIModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesSection(modifier: Modifier = Modifier, images: State<List<MovieImageUIModel>?>) {
    /* Column {*/
    val pagerState = rememberPagerState { images.value?.size ?: 0 }
    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = pagerState,
        pageSpacing = 4.dp,
        contentPadding = PaddingValues(16.dp),
        beyondBoundsPageCount = 3,
        pageSize = PageSize.Fill,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            pagerState,
            Orientation.Horizontal
        ),
        pageContent = {
            val image = remember(it) { images.value?.get(it)?.imageUrl }
            NetworkImage(
                modifier = Modifier
                    .aspectRatio(1.778f)
                    .clip(RoundedCornerShape(10.dp)),
                image = image,
                placeHolder = R.drawable.error_placeholder,
                error = R.drawable.error_placeholder,
            )
        }
    )

    /*  PagerIndicator(
          modifier = Modifier.padding(vertical = 4.dp)
              .align(Alignment.CenterHorizontally),
          pagerState = pagerState,
          indicatorsCount = 5,
          indicatorSize = 12.dp,
          borderSize = 6.dp,
      )
  }*/
}

