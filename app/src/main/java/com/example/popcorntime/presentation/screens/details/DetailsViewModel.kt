package com.example.popcorntime.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.Backdrop
import com.example.popcorntime.data.models.Language
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
    private var movieBackDrop: String? = null
    fun getMovie(movieID: Int, language: Language) {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                getImages(
                    movieID = movieID,
                    language = language
                )

                try {
                    val movie: Movie = moviesRepository.getMovie(
                        movieID = movieID,
                        language = language
                    )
                    movie.backdropPath?.let { movieBackDrop = it }
                    _movie.value = UIState.Success(movie)
                } catch (exception: Exception) {
                    _movie.value =
                        UIState.Failure(exception.localizedMessage ?: "Something went wrong")
                }
            }
    }


    private suspend fun getImages(movieID: Int, language: Language) {
        try {
            val images = moviesRepository.getImages(
                movieID = movieID,
                language = language
            )
            movieBackDrop?.let { images.backdrops?.add(Backdrop(filePath = it)) }
            _images.value = UIState.Success(images.backdrops)
        } catch (exception: Exception) {
            _images.value = UIState.Failure(exception.localizedMessage ?: "Something went wrong")
        }
    }
}

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