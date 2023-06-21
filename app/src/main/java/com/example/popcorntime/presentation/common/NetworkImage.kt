package com.example.popcorntime.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    placeHolder: @Composable () -> Unit,
    error: @Composable () -> Unit,
    contentDescription: String
) {
    var isLoading: Int by rememberSaveable { mutableStateOf(0) }
    when (isLoading) {

        0 -> placeHolder()

        -1 -> error()
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)

            .listener(object : ImageRequest.Listener {
                override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                    super.onSuccess(request, result)
                    isLoading = 1
                }

                override fun onError(request: ImageRequest, result: ErrorResult) {
                    super.onError(request, result)
                    isLoading = -1
                }
            })
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}
