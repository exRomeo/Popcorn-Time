package com.example.popcorntime.modules.movies_listing.di.module

import com.example.popcorntime.modules.movies_listing.data.data_source.remote.MoviesListDataSource
import com.example.popcorntime.modules.movies_listing.data.data_source.remote.MoviesListDataSourceImpl
import com.example.popcorntime.modules.movies_listing.data.repository.MoviesListRepositoryImpl
import com.example.popcorntime.modules.movies_listing.data.retrofit.service.MoviesListApi
import com.example.popcorntime.modules.movies_listing.domain.repository.MoviesListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviesListingModule {

    companion object {
        @Provides
        @ViewModelScoped
        fun provideMoviesListService(
            retrofit: Retrofit
        ): MoviesListApi =
            retrofit.create(MoviesListApi::class.java)

    }

    @Binds
    @ViewModelScoped
    abstract fun bindsMoviesListDataSource(
        moviesListDataSource: MoviesListDataSourceImpl
    ): MoviesListDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindsMoviesListRepository(
        moviesListRepository: MoviesListRepositoryImpl
    ): MoviesListRepository

}