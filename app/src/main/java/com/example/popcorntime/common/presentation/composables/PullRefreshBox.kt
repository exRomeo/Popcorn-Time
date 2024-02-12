package com.example.popcorntime.common.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.contentColorFor
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
/**
 * [content] should be scrollable for pull to refresh to work
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    indicatorBackgroundColor: Color = MaterialTheme.colors.surface,
    indicatorContentColor: Color = contentColorFor(indicatorBackgroundColor),
    refreshingState: State<Boolean>,
    onRefresh: () -> Unit,
    pullRefreshState: PullRefreshState = rememberPullRefreshState(
        refreshing = refreshingState.value,
        onRefresh = onRefresh
    ),
    content: @Composable BoxScope.() -> Unit
) {

    Box(
        modifier = modifier.pullRefresh(pullRefreshState),
        contentAlignment = contentAlignment
    ) {

        with(this) {
            content()
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshingState.value,
            state = pullRefreshState,
            backgroundColor = indicatorBackgroundColor,
            contentColor = indicatorContentColor
        )
    }
}

