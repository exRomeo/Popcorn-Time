package com.example.popcorntime.old_needs_sorting.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.core.state.Language
import com.example.popcorntime.old_needs_sorting.core.state.UIState
import com.example.popcorntime.old_needs_sorting.core.utils.ConnectionUtil
import com.example.popcorntime.old_needs_sorting.data.models.Backdrop
import com.example.popcorntime.old_needs_sorting.data.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    private var _movie: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val movie = _movie.asStateFlow()

    private var _images: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val images = _images.asStateFlow()
    fun getMovie(movieID: Int, language: Language) {
        _movie.value = UIState.Loading
        _images.value = UIState.Loading
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                try {

                    val images = moviesRepository.getImages(
                        movieID = movieID,
                        language = language
                    )

                    val movie: MovieDataModel = moviesRepository.getMovie(
                        movieID = movieID,
                        language = language
                    )

                    movie.backdropPath?.let { images.backdrops?.add(Backdrop(filePath = it)) }

                    _images.value = UIState.Success(images.backdrops)

                    _movie.value = UIState.Success(movie)

                } catch (exception: Exception) {
                    _movie.value =
                        UIState.Failure(exception.localizedMessage ?: "Something went wrong")
                    _images.value =
                        UIState.Failure(exception.localizedMessage ?: "Something went wrong")
                }
            }
        else
            _movie.value = UIState.NotConnected
    }
}
