package com.example.popcorntime

import android.app.Application
import com.example.popcorntime.core.helpers.MovieAPI
import com.example.popcorntime.core.helpers.TheMovieDBClient
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.datasource.MoviesSource
import com.example.popcorntime.data.repository.IMoviesRepository
import com.example.popcorntime.data.repository.MoviesRepository

class PopcornTimeApplication : Application() {

    private val movieAPI: MovieAPI by lazy {
        TheMovieDBClient(applicationContext).moviesAPI
    }

    val moviesRepository: IMoviesRepository by lazy {
        MoviesRepository(MoviesSource(movieAPI = movieAPI))
    }

    val connectionUtil: ConnectionUtil by lazy {
        ConnectionUtil(applicationContext)
    }
}