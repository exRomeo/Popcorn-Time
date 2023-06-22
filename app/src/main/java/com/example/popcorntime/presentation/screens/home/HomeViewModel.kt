package com.example.popcorntime.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popcorntime.core.state.Language
import com.example.popcorntime.core.state.SearchWidgetState
import com.example.popcorntime.core.state.SortBy
import com.example.popcorntime.core.state.UIState
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.pagingsource.MoviePagingSource
import com.example.popcorntime.data.pagingsource.MovieSearchPagingSource
import com.example.popcorntime.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    private var _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state = _state.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.Closed)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    var filter: SortBy = SortBy.Popular
    var language: Language = Language.English

    init {
        getMovies()
    }

    fun getMovies() {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                _state.value = UIState.Loading
                val moviesPager: Flow<PagingData<Movie>> = Pager(
                    PagingConfig(pageSize = 20)
                ) {
                    MoviePagingSource(filter, language, moviesRepository)
                }.flow.cachedIn(viewModelScope)
                _state.value = UIState.Success(moviesPager)
            }
        else
            _state.value = UIState.NotConnected
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun movieSearch(query: String) {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                _state.value = UIState.Loading
                val moviesPager: Flow<PagingData<Movie>> = Pager(
                    PagingConfig(pageSize = 20)
                ) {
                    MovieSearchPagingSource(
                        query = query,
                        language = language,
                        moviesRepository = moviesRepository
                    )
                }.flow.cachedIn(viewModelScope)
                _state.value = UIState.Success(moviesPager)
            }
        else
            _state.value = UIState.NotConnected
    }
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