package com.example.popcorntime.common.presentation.extentions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> Flow<T>.ObserveEvents(key: Any? = null, onEvent: suspend (T) -> Unit) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(
        key1 = this,
        key2 = lifeCycleOwner,
        key3 = key,
        block = {
            lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                withContext(Dispatchers.Main.immediate) {
                    collect(onEvent)
                }
            }
        }
    )
}