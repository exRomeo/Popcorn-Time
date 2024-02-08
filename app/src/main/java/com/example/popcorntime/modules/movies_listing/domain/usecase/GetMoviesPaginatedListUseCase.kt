package com.example.popcorntime.modules.movies_listing.domain.usecase

import com.example.popcorntime.modules.movies_listing.domain.models.MoviesListPagingDomainModel
import com.example.popcorntime.modules.movies_listing.domain.repository.MoviesListRepository
import javax.inject.Inject

class GetMoviesPaginatedListUseCase @Inject constructor(
    private val repository: MoviesListRepository
) {
    suspend operator fun invoke(
        sortBy: String,
        language: String,
        page: Int
    ): MoviesListPagingDomainModel =
        repository.getPaginatedMovies(sortBy, language, page)
}