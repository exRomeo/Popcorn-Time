package com.example.popcorntime.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val connectionUtil: ConnectionUtil
) : ViewModel() {

    private var _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state = _state.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.Closed)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    var filter: SortBy = SortBy.Popular
    var language: Language = Language.English
    var isSearching = false


    init {
        getMovies()
        observeSearchText()
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

    fun refresh() {
        if (!isSearching)
            getMovies()
        else
            movieSearch(searchTextState.value)
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
        if (newValue == SearchWidgetState.Closed && isSearching) {
            isSearching = false
            getMovies()
        }
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue

    }

    @OptIn(FlowPreview::class)
    private fun observeSearchText() {
        viewModelScope.launch {
            searchTextState.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(1000)
                .collectLatest {
                    Log.i("TAG", "updateSearchTextState: it is ->$it<-")
                    isSearching = true
                    movieSearch(it)
                }
        }
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