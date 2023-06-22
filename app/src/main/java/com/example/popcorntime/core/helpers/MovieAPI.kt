package com.example.popcorntime.core.helpers

import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{sort_by}")
    @Headers("accept: application/json")
    suspend fun getMovies(
        @Header("Authorization") apiKey: String,
        @Path("sort_by") sortBy: String,
        @Query("language") language: String,
        @Query("page") page: UInt
    ): MoviesResponse

    @GET("movie/{movie_id}")
    @Headers("accept: application/json")
    suspend fun getMovie(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieID: Int,
        @Query("language") language: String
    ): Movie

    @GET("movie/{movie_id}/images")
    @Headers("accept: application/json")
    suspend fun getImages(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieID: Int,
        @Query("include_image_language") language: String
    ): BackDropsResponse

}