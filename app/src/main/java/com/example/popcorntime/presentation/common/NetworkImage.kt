package com.example.popcorntime.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes error: Int,
    contentDescription: String
) {
    var isLoading: Int by rememberSaveable { mutableStateOf(0) }
    when (isLoading) {

        0 -> AnimatedShimmer {
            Box(
                Modifier
                    .aspectRatio(2 / 3f)
                    .background(it)
            )
        }

        -1 -> Box(
            Modifier
                .aspectRatio(2 / 3f)

        ) {
            Image(painter = painterResource(id = error), contentDescription = "error")
        }
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
