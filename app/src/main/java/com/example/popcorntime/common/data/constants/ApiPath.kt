package com.example.popcorntime.common.data.constants

object ApiPath {
    const val MOVIE = "movie"
    const val SORT_BY = "sort_by"
    const val SORT_BY_PATH = "$MOVIE/{$SORT_BY}"
    const val SEARCH = "search"
    const val MOVIE_SEARCH = "$SEARCH/$MOVIE"
}