package com.example.popcorntime.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.core.uistate.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.Language
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse
import com.example.popcorntime.data.models.SortBy
import com.example.popcorntime.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    var page: Int = 1
    private var _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state = _state.asStateFlow()

    private var _movies: MutableStateFlow<MutableList<Movie>> = MutableStateFlow(mutableListOf())
    val movies = _movies.asStateFlow()
    private var oldList = mutableListOf<Movie>()

    init {
        getMovies(SortBy.Popular, Language.English)
    }

    fun getMovies(sortBy: SortBy, language: Language) {

        if (connectionUtil.isConnected())
            viewModelScope.launch {
                val response =
                    moviesRepository.getMovies(sortBy = sortBy, language = language, page = page)
                responseHandler(response)
            }
        else
            _state.value = UIState.NotConnected
    }

    private fun responseHandler(response: Response<MoviesResponse>) {
        if (response.isSuccessful && response.body() != null) {

            response.body()!!.movies?.let {
                _state.value = UIState.Success
                oldList.addAll(it)
                _movies.value = ArrayList(oldList)
            } ?: UIState.Failure(
                "No Results"
            )
        } else {
            _state.value = UIState.Failure(response.errorBody().toString())
        }
    }

    fun loadNextPage(sortBy: SortBy, language: Language) {
        page++
        viewModelScope.launch {

            getMovies(SortBy.Popular, Language.English)
            Log.i("TAG", "loadNextPage: $page")
        }
    }
}


class HomeViewModelFactory(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(moviesRepository, connectionUtil) as T
        else throw Exception("ViewModel Not Found!")
    }
}