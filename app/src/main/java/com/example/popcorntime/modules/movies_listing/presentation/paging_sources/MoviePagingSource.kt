package com.example.popcorntime.modules.movies_listing.presentation.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorntime.common.constants.ApiConstants
import com.example.popcorntime.modules.movies_listing.domain.usecase.GetMoviesPaginatedListUseCase
import com.example.popcorntime.modules.movies_listing.presentation.mappers.toUI
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.common.presentation.models.SortBy

class MoviePagingSource(
    private val sortBy: SortBy,
    private val language: Language,
    private val getMoviesPaginatedListUseCase: GetMoviesPaginatedListUseCase
) : PagingSource<Int, MovieUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
        return try {
            val start = params.key ?: ApiConstants.STARTING_PAGE
            val response = getMoviesPaginatedListUseCase(
                sortBy = sortBy.value,
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
            ApiConstants.STARTING_PAGE -> null
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