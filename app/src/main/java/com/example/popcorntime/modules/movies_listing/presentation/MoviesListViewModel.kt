package com.example.popcorntime.modules.movies_listing.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popcorntime.common.data.constants.ApiConstants.ITEMS_PER_PAGE
import com.example.popcorntime.common.presentation.BaseMVIViewModel
import com.example.popcorntime.common.presentation.models.SearchWidgetState
import com.example.popcorntime.common.presentation.models.SortBy
import com.example.popcorntime.modules.movies_listing.domain.usecase.GetMoviesPaginatedListUseCase
import com.example.popcorntime.modules.movies_listing.domain.usecase.SearchMoviesPaginatedUseCase
import com.example.popcorntime.modules.movies_listing.presentation.contract.MovieListUIEvent
import com.example.popcorntime.modules.movies_listing.presentation.contract.MovieListUIState
import com.example.popcorntime.modules.movies_listing.presentation.contract.MovieListUserEvent
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.modules.movies_listing.presentation.paging_sources.MoviePagingSource
import com.example.popcorntime.modules.movies_listing.presentation.paging_sources.MovieSearchPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
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
) : BaseMVIViewModel<
        MovieListUserEvent,
        MovieListUIEvent,
        MovieListUIState
        >() {

    init {
        getMovies()
        observeSearchText()
    }

    override fun createInitialUIState(): MovieListUIState = MovieListUIState()

    private fun getMovies() {
        uiState.moviesListPagingSource.value = Pager(
            PagingConfig(pageSize = ITEMS_PER_PAGE)
        ) {
            MoviePagingSource(
                getMoviesPaginatedListUseCase,
                uiState.sortingState.value,
                uiState.language.value,
                onClick = ::onMovieClick
            )
        }.flow.cachedIn(viewModelScope)

    }

    private fun changeSorting(sortBy: SortBy) {
        uiState.sortingState.value = sortBy
        getMovies()
    }

    private fun onMovieClick(movie: MovieUIModel) {
        setUserEvent(MovieListUserEvent.OpenMovieDetails(movieId = movie.id))
    }

    fun refresh() {
        if (!uiState.isSearching.value)
            getMovies()
        else
            movieSearch(uiState.searchTextState.value)
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        uiState.searchWidgetState.value = newValue
        if (newValue == SearchWidgetState.Closed && uiState.isSearching.value) {
            uiState.isSearching.value = false
            getMovies()
        }
    }

    fun updateSearchTextState(newValue: String) {
        uiState.searchTextState.value = newValue

    }

    @OptIn(FlowPreview::class)
    private fun observeSearchText() {
        viewModelScope.launch {
            snapshotFlow { uiState.searchTextState.value }
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(1000)
                .collectLatest {
                    uiState.isSearching.value = true
                    movieSearch(it)
                }
        }
    }

    fun movieSearch(query: String) {
        viewModelScope.launch {
            val moviesPager: Flow<PagingData<MovieUIModel>> = Pager(
                PagingConfig(pageSize = ITEMS_PER_PAGE)
            ) {
                MovieSearchPagingSource(
                    searchMoviesUseCase = searchMoviesPaginatedUseCase,
                    query = query,
                    language = uiState.language.value,
                    onClick = ::onMovieClick
                )
            }.flow.cachedIn(viewModelScope)
            uiState.moviesListPagingSource.value = moviesPager
        }
    }

    private fun refreshScreen() {
        resetUIState()
        getMovies()
    }

    override fun resetUIState() {
        uiState.networkError.value = false
        uiState.isSearching.value = false
        uiState.searchTextState.value = ""

    }

    override fun handleUserEvent(userEvent: MovieListUserEvent) {
        when (userEvent) {

            MovieListUserEvent.Refresh -> refreshScreen()

            is MovieListUserEvent.ChangeSorting -> {
                changeSorting(userEvent.sortBy)
            }

            is MovieListUserEvent.OpenMovieDetails -> {
                userEvent.movieId?.let {
                    setUIEvent(
                        MovieListUIEvent
                            .OpenMovieDetails(movieId = it)
                    )
                }
            }

            MovieListUserEvent.CloseClicked -> {
                updateSearchTextState("")
                updateSearchWidgetState(SearchWidgetState.Closed)
            }

            is MovieListUserEvent.MovieSearch -> {
                movieSearch(userEvent.query)
            }

            is MovieListUserEvent.SearchTextChanged -> {
                updateSearchTextState(userEvent.query)
            }

            MovieListUserEvent.SearchMode -> {
                updateSearchWidgetState(SearchWidgetState.Opened)
            }
        }
    }
}