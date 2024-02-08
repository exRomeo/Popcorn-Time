package com.example.popcorntime.modules.movie_details.di

import com.example.popcorntime.modules.movie_details.presentation.DetailsViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsViewModelFactory {
    fun create(movieId: Int): DetailsViewModel
}