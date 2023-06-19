package com.example.popcorntime.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Backdrop
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    private var _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state = _state.asStateFlow()

    private var _images: MutableStateFlow<List<Backdrop>> = MutableStateFlow(listOf())
    val images = _images.asStateFlow()

    fun getMovie(movieID: Int, language: Language) {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                getImages(
                    movieID = movieID,
                    language = language
                )
                responseHandler(
                    response =
                    moviesRepository.getMovie(
                        movieID = movieID,
                        language = language
                    )
                )
            }
    }


    private fun responseHandler(response: Response<Movie>) {
        if (response.isSuccessful) {
            response.body()?.let {
                _state.value = UIState.Success(it)
            } ?: UIState.Failure(
                "No Results"
            )
        } else {
            _state.value = UIState.Failure(response.errorBody().toString())
        }
    }

    private suspend fun getImages(movieID: Int, language: Language) {
        handleImages(
            moviesRepository.getImages(
                movieID = movieID,
                language = language
            )
        )
    }

    private fun handleImages(response: Response<BackDropsResponse>) {
        if (response.isSuccessful && response.body() != null)
            response.body()!!.backdrops?.let { _images.value = it }
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