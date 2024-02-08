package com.example.popcorntime.common.presentation.composables

import androidx.compose.runtime.Composable
import com.example.popcorntime.common.presentation.extentions.ObserveEvents
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> UIEventsObserver(flow: Flow<T>, block: (T) -> Unit) {
    flow.ObserveEvents(onEvent = block)
}