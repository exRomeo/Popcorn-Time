package com.example.popcorntime.modules.movie_details.domain.usecase

import com.example.popcorntime.modules.movie_details.domain.models.MovieImagesDomainModel
import com.example.popcorntime.modules.movie_details.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {

    suspend operator fun invoke(
        movieID: Int,
        language: String
    ): MovieImagesDomainModel =
        movieDetailsRepository.getImages(
            movieID,
            language
        )
}