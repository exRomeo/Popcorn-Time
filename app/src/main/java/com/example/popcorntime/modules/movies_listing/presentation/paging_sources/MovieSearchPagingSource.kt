package com.example.popcorntime.modules.movies_listing.presentation.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorntime.common.constants.ApiConstants.STARTING_PAGE
import com.example.popcorntime.modules.movies_listing.domain.usecase.SearchMoviesPaginatedUseCase
import com.example.popcorntime.modules.movies_listing.presentation.mappers.toUI
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.common.presentation.models.Language

class MovieSearchPagingSource(
    private val query: String,
    private val language: Language,
    private val searchMoviesUseCase: SearchMoviesPaginatedUseCase
) :
    PagingSource<Int, MovieUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
        return try {
            val start = params.key ?: STARTING_PAGE
            val response =
                searchMoviesUseCase(
                    query = query,
                    language = language.value,
                    page = start
                ).toUI()

            LoadResult.Page(
                data = response.movies,
                prevKey = calculatePrevKey(start),
                nextKey = calculateNextKey(start, response.totalPages)
            )

        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }

    }

    private fun calculatePrevKey(start: Int): Int? {
        return when (start) {
            STARTING_PAGE -> null
            else -> start - 1
        }
    }

    private fun calculateNextKey(start: Int, totalPages: Int): Int? {
        return when {
            start < totalPages -> start + 1
            else -> null
        }
    }
}