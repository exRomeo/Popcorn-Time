package com.example.popcorntime.common.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays a set of indicators for a pager.
 * @param modifier The modifier for the composable.
 * @param pagerState The [PagerState] that represents the state of the pager.
 * @param indicatorsCount is the total number of visible indicators.
 * @param indicatorSize The size of each indicator.
 * @param indicatorShape The shape of the indicators (default is [CircleShape]).
 * @param space The space between indicators (default is 8.dp).
 * @param activeColor The color of the active indicator (default is red).
 * @param inActiveColor The color of the inactive indicators (default is secondary).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorsCount: Int,
    indicatorSize: Dp,
    indicatorShape: Shape = CircleShape,
    space: Dp = 8.dp,
    borderSize: Dp = 1.dp,
    activeColor: Color = MaterialTheme.colors.error,
    inActiveColor: Color = MaterialTheme.colors.secondary
) {
    //remembering the lazy list state to be able to manage the scroll of the indicators
    val listState = rememberLazyListState()
    val listSize = remember { pagerState.pageCount }
    val indicatorCount = remember { if (listSize < indicatorsCount) listSize else indicatorsCount }

    //determines the middle indicator index which is used to determine the right and left edge items
    val centerIndicatorIndex = remember(indicatorCount) { indicatorCount / 2 }

    // Calculating the total width of the indicators row based on the indicator size and space between them
    val totalWidth: Dp =
        remember(indicatorCount) { (indicatorSize * indicatorCount) + (space * (indicatorCount - 1)) }

    //converting the indicator size to pixels to be able to calculate the scroll offset which is used to center the middle indicator
    val widthInPx = LocalDensity.current.run { indicatorSize.toPx() }

    //the pager has an infinite number of pages so we need to calculate the current item based on the settled page using the modulo operator
    val currentItem by remember(listSize) {
        derivedStateOf {
            pagerState.currentPage % listSize
        }
    }


    // Scroll to the current item when it changes
    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        val scrollOffset = (widthInPx.toInt() - viewportSize.width) / 2
        listState.animateScrollToItem(
            currentItem, scrollOffset,
        )
    }

    LazyRow(
        modifier = modifier
            .width(totalWidth),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(space),
        userScrollEnabled = false
    ) {
        items(listSize) { index ->

            //determines if the indicator is selected
            val isSelected = remember(currentItem) { { index == currentItem } }

            //determines the right edge item
            val isRightEdgeItem = remember(currentItem) {
                //case to determine the right edge item when the indicator is not at the middle yet -o0ooọ-
                currentItem < centerIndicatorIndex &&
                        index >= indicatorCount - 1 ||
                        //case to determine the right edge item when the indicator is at the middle -oo0oọ-
                        currentItem >= centerIndicatorIndex &&
                        index >= currentItem + centerIndicatorIndex &&
                        index <= listSize - centerIndicatorIndex + 1 /*||*/
                        //case for right most item when the indicator moves to the right of the middle -ooo0ọ-
/*
                        index == listSize - 1
*/
            }


            //determines the left edge item
            val isLeftEdgeItem = remember(currentItem) {
                //case to determine the left edge item when the indicator is at the middle or when its farther away -ọo0oo- || -ọoo0o-
                index <= currentItem - centerIndicatorIndex &&
                        currentItem > centerIndicatorIndex &&
                        index < listSize - indicatorCount + 1 /*||*/
                        //case for left most item when the indicator moves to the left of the middle -ọ0ooo-
/*
                        index == 0
*/
            }

            //determines the scale of the indicator based on the selected state and the edge states
            val scale = remember(currentItem) {
                when {
                    //when the indicator is selected item is at full scale
                    isSelected() -> 1f
                    //when indicator is not selected and at the left edge or right edge item is at half scale
                    (isLeftEdgeItem || isRightEdgeItem) -> .5f
                    //when indicator is not selected and not at the edges is at 3/4 scale
                    else -> .75f
                }
            }

            //determines the color of the border of the indicator based on the selected state
            val borderColor = remember(currentItem) {
                when {
                    isSelected() -> activeColor
                    else -> inActiveColor
                }
            }

            Box(modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .clip(indicatorShape)
                .size(indicatorSize)
                .border(
                    width = borderSize,
                    color = borderColor,
                    shape = CircleShape
                )
                .background(
                    MaterialTheme.colors
                        .surface,
                    indicatorShape
                )
            )
        }
    }
}