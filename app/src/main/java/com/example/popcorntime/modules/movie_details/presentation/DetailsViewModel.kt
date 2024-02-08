package com.example.popcorntime.modules.movie_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.common.presentation.models.UIState
import com.example.popcorntime.modules.movie_details.domain.models.MovieImageDomainModel
import com.example.popcorntime.modules.movie_details.domain.usecase.GetMovieDetailsUseCase
import com.example.popcorntime.modules.movie_details.domain.usecase.GetMovieImagesUseCase
import com.example.popcorntime.modules.movie_details.presentation.mappers.toUI
import com.example.popcorntime.modules.movies_listing.presentation.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
) : ViewModel() {

    private var _movie: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val movie = _movie.asStateFlow()

    private var _images: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val images = _images.asStateFlow()
    fun getMovie(movieID: Int, language: Language) {
        _movie.value = UIState.Loading
        _images.value = UIState.Loading
        viewModelScope.launch {
            try {

                val images = getMovieImagesUseCase(
                    movieID = movieID,
                    language = language.value
                ).images?.toMutableList()

                val movie = getMovieDetailsUseCase(
                    movieID = movieID,
                    language = language.value
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

                _images.value = UIState.Success(images?.map { it.toUI() })

                _movie.value = UIState.Success(movie.toUI())

            } catch (exception: Exception) {
                _movie.value =
                    UIState.Failure(exception.localizedMessage ?: "Something went wrong")
                _images.value =
                    UIState.Failure(exception.localizedMessage ?: "Something went wrong")
            }
        }
    }
}
