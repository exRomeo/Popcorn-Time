package com.example.popcorntime.modules.movie_details.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.popcorntime.modules.movies_listing.presentation.models.GenreUIModel

@Composable
fun GenreItem(genres: List<GenreUIModel>) {
    LazyRow {
        items(genres) {
            AssistChip(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
                label = { Text(text = it.name.orEmpty()) }
            )
        }
    }
}