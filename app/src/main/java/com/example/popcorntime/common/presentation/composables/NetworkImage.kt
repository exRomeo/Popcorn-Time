package com.example.popcorntime.common.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    image: Any?,
    placeHolder: @Composable () -> Unit,
    error: @Composable () -> Unit,
    contentDescription: String? = null
) {
    var isLoading: Int by rememberSaveable { mutableIntStateOf(-1) }
    when (isLoading) {
        0 -> error()
        1 -> placeHolder()
    }

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
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
        onState = { state ->
            isLoading = when (state) {
                AsyncImagePainter.State.Empty -> -1
                is AsyncImagePainter.State.Error -> 0
                is AsyncImagePainter.State.Loading -> 1
                is AsyncImagePainter.State.Success -> 2
            }
        }
    )
}
