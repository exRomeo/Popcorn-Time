package com.example.popcorntime.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @field:SerializedName("page")
    val page: Long? = null,
    @field:SerializedName("results")
    val movies: List<Movie>? = null,
    @field:SerializedName("total_pages")
    val totalPages: Long? = null,
    @field:SerializedName("total_results")
    val totalResults: Long? = null
)