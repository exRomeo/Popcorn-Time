package com.example.popcorntime.modules.movies_listing.data.retrofit.response

import androidx.annotation.Keep
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDataModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)