package com.example.popcorntime.data.datasource

import com.example.popcorntime.data.models.BackDropsResponse
import com.example.popcorntime.data.models.Movie
import com.example.popcorntime.data.models.MoviesResponse

interface IMoviesSource {
    suspend fun getMovies(sortBy: String, language: String, page: UInt): MoviesResponse
    suspend fun getMovie(movieID: Int, language: String): Movie
    suspend fun getImages(movieID: Int, language: String): BackDropsResponse

}