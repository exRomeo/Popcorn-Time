package com.example.popcorntime.modules.movies_listing.data.retrofit.service

import com.example.popcorntime.common.constants.ApiPath.MOVIE_SEARCH
import com.example.popcorntime.common.constants.ApiPath.SORT_BY
import com.example.popcorntime.common.constants.ApiPath.SORT_BY_PATH
import com.example.popcorntime.common.constants.ApiQuery.INCLUDE_ADULT
import com.example.popcorntime.common.constants.ApiQuery.LANGUAGE
import com.example.popcorntime.common.constants.ApiQuery.PAGE
import com.example.popcorntime.common.constants.ApiQuery.QUERY
import com.example.popcorntime.modules.movies_listing.data.retrofit.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesListApi {

    @GET(SORT_BY_PATH)
    @Headers("accept: application/json", "Cache-Control: max-age=3600")
    suspend fun getMovies(
        @Header("Authorization") apiKey: String,
        @Path(SORT_BY) sortBy: String,
        @Query(LANGUAGE) language: String,
        @Query(PAGE) page: Int
    ): MoviesResponse

    @GET(MOVIE_SEARCH)
    @Headers("accept: application/json", "Cache-Control: max-age=3600")
    suspend fun movieSearch(
        @Header("Authorization") apiKey: String,
        @Query(QUERY) query: String,
        @Query(INCLUDE_ADULT) includeAdult: Boolean,
        @Query(LANGUAGE) language: String,
        @Query(PAGE) page: Int
    ): MoviesResponse

}