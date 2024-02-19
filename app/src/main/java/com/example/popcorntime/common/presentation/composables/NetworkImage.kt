package com.example.popcorntime.common.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    image: Any?,
    @DrawableRes placeHolder: Int,
    @DrawableRes error: Int,
    contentDescription: String? = null
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .decoderDispatcher(Dispatchers.Default)
            .crossfade(true)
            .error(error)
            .placeholder(placeHolder)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
    )
}
