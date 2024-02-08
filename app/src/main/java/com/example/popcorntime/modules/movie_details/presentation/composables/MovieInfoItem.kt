package com.example.popcorntime.modules.movie_details.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MovieInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    data: String,
    imageVector: ImageVector,
    contentDescription: String
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
        Text(text = title)
        Text(text = data)
    }
}