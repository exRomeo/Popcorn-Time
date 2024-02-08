package com.example.popcorntime.modules.movie_details.di

import com.example.popcorntime.modules.movie_details.data.remote.MovieDetailsDataSource
import com.example.popcorntime.modules.movie_details.data.remote.MovieDetailsDataSourceImpl
import com.example.popcorntime.modules.movie_details.data.repository.MovieDetailsRepositoryImpl
import com.example.popcorntime.modules.movie_details.data.retrofit.service.MovieDetailsApi
import com.example.popcorntime.modules.movie_details.domain.repository.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDetailsModule {

    companion object {
        @Provides
        @ViewModelScoped
        fun providesMovieDetailsApi(retrofit: Retrofit): MovieDetailsApi {
            return retrofit.create(MovieDetailsApi::class.java)
        }
    }

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailsDataSource(
        movieDetailsDataSource: MovieDetailsDataSourceImpl
    ): MovieDetailsDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailsRepository(
        movieDetailsRepository: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

}