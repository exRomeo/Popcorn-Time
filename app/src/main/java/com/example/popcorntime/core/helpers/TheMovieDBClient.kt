package com.example.popcorntime.core.helpers

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient(context: Context) {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    }

    private val cache = Cache(context.cacheDir, CACHE_SIZE)
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().cache(cache).build()
    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    val moviesAPI: MovieAPI = retrofit.create(MovieAPI::class.java)
}