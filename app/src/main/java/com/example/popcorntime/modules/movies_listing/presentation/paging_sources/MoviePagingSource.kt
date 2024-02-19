package com.example.popcorntime.modules.movies_listing.presentation.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorntime.common.data.constants.ApiConstants
import com.example.popcorntime.common.presentation.extentions.orElse
import com.example.popcorntime.common.presentation.models.Language
import com.example.popcorntime.common.presentation.models.SortBy
import com.example.popcorntime.modules.movies_listing.domain.usecase.GetMoviesPaginatedListUseCase
import com.example.popcorntime.modules.movies_listing.presentation.mappers.toUI
import com.example.popcorntime.modules.movies_listing.presentation.models.MovieUIModel

class MoviePagingSource(
    private val getMoviesPaginatedListUseCase: GetMoviesPaginatedListUseCase,
    private val sortBy: SortBy,
    private val language: Language,
    private val onClick: (MovieUIModel) -> Unit,
) : PagingSource<Int, MovieUIModel>() {

    private val STARTING_KEY = ApiConstants.STARTING_PAGE
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
        return try {
            val start = params.key ?: STARTING_KEY
            val response = getMoviesPaginatedListUseCase(
                sortBy = sortBy.value,
                language = language.value,
                page = start
            ).toUI(onClick = onClick)

            LoadResult.Page(
                data = response.movies.orEmpty(),
                prevKey = calculatePrevKey(start),
                nextKey = calculateNextKey(
                    start,
                    response.totalPages ?: response.page?.plus(1) ?: 0
                )
            )

        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }


    private fun calculatePrevKey(start: Int): Int? {
        return when (start) {
            STARTING_KEY -> null
            else -> start.minus(1).ensureValidKey(STARTING_KEY) { it > STARTING_KEY }
        }
    }

    private fun calculateNextKey(start: Int, totalPages: Int?): Int? {
        return start.plus(1).ensureValidKey { it <= totalPages.orElse(0) }
    }

    private fun <T> T?.ensureValidKey(backup: T? = null, predicate: (T) -> Boolean): T? {
        if (this == null) return backup
        return if (predicate(this)) this else backup
    }
}