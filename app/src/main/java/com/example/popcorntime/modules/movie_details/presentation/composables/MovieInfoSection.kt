package com.example.popcorntime.modules.movie_details.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.popcorntime.R
import com.example.popcorntime.common.presentation.composables.CircleWithPercentage
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

@Composable
fun MovieInfoSection(
    modifier: Modifier = Modifier,
    movie: State<MovieUIModel?>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 4.dp
        )
    ) {
        item {
            CircleWithPercentage(
                percentage = movie.value?.average ?: 0f,
                stringResource(id = R.string.average)
            )
        }
        item {
            MovieInfoItem(
                title = stringResource(R.string.vote),
                data = (movie.value?.voteCount ?: 0).toString(),
                imageVector = Icons.Default.BarChart,
                contentDescription = stringResource(R.string.vote)
            )
        }
        item {
            MovieInfoItem(
                title = stringResource(R.string.language),
                data = movie.value?.originalLanguage.orEmpty(),
                imageVector = Icons.Default.Language,
                contentDescription = stringResource(R.string.language)
            )
        }
        item {
            MovieInfoItem(
                title = stringResource(R.string.status),
                data = movie.value?.status.orEmpty(),
                imageVector = Icons.Default.NewReleases,
                contentDescription = stringResource(R.string.status)
            )
        }
        item {
            MovieInfoItem(
                title = stringResource(R.string.date),
                data = movie.value?.releaseDate.orEmpty(),
                imageVector = Icons.Default.DateRange,
                contentDescription = stringResource(R.string.date)
            )
        }
    }
}