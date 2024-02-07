package com.example.popcorntime.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorntime.core.state.Language
import com.example.popcorntime.core.state.SortBy
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.repository.IMoviesRepository

class MoviePagingSource(
    private val sortBy: SortBy,
    private val language: Language,
    private val moviesRepository: IMoviesRepository
) : PagingSource<Int, Movie>() {
    companion object {
        const val STARTING_KEY = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey ?: page?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val start = params.key ?: STARTING_KEY
            /*
                        val range = start until  (start + params.loadSize)
            */
            val response =
                moviesRepository.getMovies(
                    sortBy = sortBy,
                    language = language,
                    page = start
                )

            LoadResult.Page(
                data = response.movies,
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(start - 1)
                },
                nextKey = if (start < response.totalPages) response.page + 1 else null
            )

        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    private fun ensureValidKey(key: Int) = maxOf(STARTING_KEY, key)

}