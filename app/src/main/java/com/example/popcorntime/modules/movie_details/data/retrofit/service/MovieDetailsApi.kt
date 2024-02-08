package com.example.popcorntime.modules.movie_details.data.retrofit.service

import com.example.popcorntime.modules.movie_details.data.retrofit.response.MovieImagesResponse
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {
    @GET("movie/{movie_id}")
    @Headers("accept: application/json", "Cache-Control: max-age=3600")
    suspend fun getMovieDetails(
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
    ): MovieImagesResponse
}