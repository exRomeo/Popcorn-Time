package com.example.popcorntime.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.core.state.Language
import com.example.popcorntime.core.state.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.Backdrop
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
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

                    val movie: Movie = moviesRepository.getMovie(
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

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            DetailsViewModel(
                moviesRepository = moviesRepository,
                connectionUtil = connectionUtil
            ) as T
        else throw Exception("ViewModel Not Found!")
    }
}