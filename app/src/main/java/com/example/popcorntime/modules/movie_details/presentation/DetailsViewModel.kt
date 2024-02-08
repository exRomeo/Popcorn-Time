package com.example.popcorntime.modules.movie_details.presentation

import androidx.lifecycle.viewModelScope
import com.example.popcorntime.common.presentation.BaseMVIViewModel
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.modules.movie_details.di.DetailsViewModelFactory
import com.example.popcorntime.modules.movie_details.domain.models.MovieImageDomainModel
import com.example.popcorntime.modules.movie_details.domain.usecase.GetMovieDetailsUseCase
import com.example.popcorntime.modules.movie_details.domain.usecase.GetMovieImagesUseCase
import com.example.popcorntime.modules.movie_details.presentation.contract.MovieDetailsUIEvent
import com.example.popcorntime.modules.movie_details.presentation.contract.MovieDetailsUIState
import com.example.popcorntime.modules.movie_details.presentation.contract.MovieDetailsUserEvent
import com.example.popcorntime.modules.movie_details.presentation.mappers.toUI
import com.example.popcorntime.modules.movies_listing.presentation.mappers.toUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    @Assisted private val movieID: Int,
) : BaseMVIViewModel<MovieDetailsUserEvent, MovieDetailsUIEvent, MovieDetailsUIState>() {


    init {
        getMovie(Language.English)
    }

    override fun createInitialUIState(): MovieDetailsUIState = MovieDetailsUIState()
    private fun getMovie(
        language: Language
    ): Job {
        return viewModelScope.launch {
            delay(1000)
            try {
                val images = getMovieImagesUseCase(
                    movieID = movieID, language = language.value
                ).images?.toMutableList()

                val movie = getMovieDetailsUseCase(
                    movieID = movieID, language = language.value
                )

                movie.backdropPath?.let {
                    images?.add(
                        MovieImageDomainModel(
                            filePath = it,
                            aspectRatio = null,
                            width = null,
                            height = null,
                            voteAverage = null,
                            voteCount = null
                        )
                    )
                }

                uiState.images.value = images?.map { it.toUI() }
                uiState.movie.value = movie.toUI()

            } catch (exception: Exception) {
                uiState.networkError.value = true
            }
        }
    }

    private fun refreshScreen() {
        viewModelScope.launch {
            resetUIState()
            uiState.loading.value = true
            getMovie(Language.English).join()
            uiState.loading.value = false
        }

    }

    override fun resetUIState() {
        uiState.networkError.value = false
        uiState.images.value = null
        uiState.movie.value = null
    }

    override fun handleUserEvent(userEvent: MovieDetailsUserEvent) {
        when (userEvent) {
            MovieDetailsUserEvent.RefreshScreen -> {
                refreshScreen()
            }

            MovieDetailsUserEvent.BackClicked -> {
                setUIEvent(MovieDetailsUIEvent.NavigateBack)
            }
        }
    }
}
