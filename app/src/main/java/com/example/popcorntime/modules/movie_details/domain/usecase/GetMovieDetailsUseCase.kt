package com.example.popcorntime.modules.movie_details.domain.usecase

import com.example.popcorntime.modules.movie_details.domain.models.MovieDetailsDomainModel
import com.example.popcorntime.modules.movie_details.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(
        movieID: Int,
        language: String
    ): MovieDetailsDomainModel =
        repository.getMovieDetails(
            movieID,
            language
        )

}