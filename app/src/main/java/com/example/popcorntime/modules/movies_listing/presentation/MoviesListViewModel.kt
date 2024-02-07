package com.example.popcorntime.modules.movies_listing.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popcorntime.common.constants.ApiConstants.ITEMS_PER_PAGE
import com.example.popcorntime.modules.movies_listing.domain.usecase.GetMoviesPaginatedListUseCase
import com.example.popcorntime.modules.movies_listing.domain.usecase.SearchMoviesPaginatedUseCase
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.old_needs_sorting.core.state.Language
import com.example.popcorntime.old_needs_sorting.core.state.SearchWidgetState
import com.example.popcorntime.old_needs_sorting.core.state.SortBy
import com.example.popcorntime.old_needs_sorting.core.state.UIState
import com.example.popcorntime.old_needs_sorting.core.utils.ConnectionUtil
import com.example.popcorntime.old_needs_sorting.data.pagingsource.MoviePagingSource
import com.example.popcorntime.old_needs_sorting.data.pagingsource.MovieSearchPagingSource
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
class MoviesListViewModel @Inject constructor(
    private val getMoviesPaginatedListUseCase: GetMoviesPaginatedListUseCase,
    private val searchMoviesPaginatedUseCase: SearchMoviesPaginatedUseCase,
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
                val moviesPager: Flow<PagingData<MovieUIModel>> = Pager(
                    PagingConfig(pageSize = ITEMS_PER_PAGE)
                ) {
                    MoviePagingSource(filter, language, getMoviesPaginatedListUseCase)
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
                    isSearching = true
                    movieSearch(it)
                }
        }
    }

    fun movieSearch(query: String) {
        if (connectionUtil.isConnected())
            viewModelScope.launch {
                _state.value = UIState.Loading
                val moviesPager: Flow<PagingData<MovieUIModel>> = Pager(
                    PagingConfig(pageSize = ITEMS_PER_PAGE)
                ) {
                    MovieSearchPagingSource(
                        query = query,
                        language = language,
                        searchMoviesUseCase = searchMoviesPaginatedUseCase
                    )
                }.flow.cachedIn(viewModelScope)
                _state.value = UIState.Success(moviesPager)
            }
        else
            _state.value = UIState.NotConnected
    }
}