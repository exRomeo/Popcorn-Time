package com.example.popcorntime.old_needs_sorting.core.service

import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.example.popcorntime.old_needs_sorting.data.models.BackDropsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{movie_id}")
    @Headers("accept: application/json", "Cache-Control: max-age=3600")
    suspend fun getMovie(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieID: Int,
        @Query("language") language: String
    ): MovieDataModel

    @GET("movie/{movie_id}/images")
    @Headers("accept: application/json", "Cache-Control: max-age=3600")
    suspend fun getImages(
        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieID: Int,
        @Query("include_image_language") language: String
    ): BackDropsResponse
}