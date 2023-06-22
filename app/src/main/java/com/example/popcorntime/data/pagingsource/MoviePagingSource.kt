package com.example.popcorntime.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorntime.core.state.Language
import com.example.popcorntime.core.state.SortBy
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.repository.IMoviesRepository
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val sortBy: SortBy,
    private val language: Language,
    private val moviesRepository: IMoviesRepository
) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response =
                moviesRepository.getMovies(
                    sortBy = sortBy,
                    language = language,
                    page = page
                )

            LoadResult.Page(
                data = response.movies,
                prevKey = null,
                nextKey = if (page <= response.totalPages) response.page + 1 else null
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}