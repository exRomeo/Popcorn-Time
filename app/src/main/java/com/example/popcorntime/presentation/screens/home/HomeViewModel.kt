package com.example.popcorntime.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.SortBy
import com.example.popcorntime.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    var page: Int = 1
    private var _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state = _state.asStateFlow()

    private var _movies: MutableStateFlow<MutableList<Movie>> = MutableStateFlow(mutableListOf())
    val movies = _movies.asStateFlow()

    init {
        getMovies(SortBy.Popular, Language.English)
    }

    fun getMovies(sortBy: SortBy, language: Language) {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                try {
                    val response =
                        moviesRepository.getMovies(
                            sortBy = sortBy,
                            language = language,
                            page = page
                        )
                    _state.value = UIState.Success(response.movies)
                } catch (exception: Exception) {
                    _state.value =
                        UIState.Failure(exception.localizedMessage ?: "Something went Wrong")
                }
            }
        else
            _state.value = UIState.NotConnected
    }

    fun refresh(sortBy: SortBy, language: Language) {
        if (connectionUtil.isConnected()) {
            _state.value = UIState.Loading
            getMovies(sortBy, language)
        } else {
            _state.value = UIState.NotConnected
        }
    }

//    fun loadNextPage(sortBy: SortBy, language: Language) {
//        page++
//        viewModelScope.launch {
//
//            getMovies(SortBy.Popular, Language.English)
//            Log.i("TAG", "loading page number: $page")
//        }
//    }
}


class HomeViewModelFactory(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(
                moviesRepository = moviesRepository,
                connectionUtil = connectionUtil
            ) as T
        else throw Exception("ViewModel Not Found!")
    }
}