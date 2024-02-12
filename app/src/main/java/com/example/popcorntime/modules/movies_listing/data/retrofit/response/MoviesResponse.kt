package com.example.popcorntime.modules.movies_listing.data.retrofit.response

import androidx.annotation.Keep
import com.example.popcorntime.modules.movies_listing.data.models.MovieDataModel
import com.google.gson.annotations.SerializedName

@Keep
class MoviesResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val movies: List<MovieDataModel>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)