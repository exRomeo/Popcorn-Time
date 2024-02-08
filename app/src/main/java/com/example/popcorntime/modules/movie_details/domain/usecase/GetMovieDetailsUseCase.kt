package com.example.popcorntime.modules.movie_details.domain.usecase

import com.example.popcorntime.modules.movie_details.domain.repository.MovieDetailsRepository
import com.example.popcorntime.modules.movies_listing.domain.models.MovieDomainModel
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(
        movieID: Int,
        language: String
    ): MovieDomainModel =
        repository.getMovieDetails(
            movieID,
            language
        )

}