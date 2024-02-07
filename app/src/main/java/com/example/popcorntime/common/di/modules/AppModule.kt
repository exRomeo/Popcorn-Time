package com.example.popcorntime.common.di.modules


import android.app.Application
import android.content.Context
import com.example.popcorntime.BuildConfig
import com.example.popcorntime.common.constants.ApiConstants.BASE_URL
import com.example.popcorntime.old_needs_sorting.core.service.MovieAPI
import com.example.popcorntime.old_needs_sorting.core.utils.ConnectionUtil
import com.example.popcorntime.old_needs_sorting.data.datasource.IMoviesSource
import com.example.popcorntime.old_needs_sorting.data.datasource.MoviesSourceImpl
import com.example.popcorntime.old_needs_sorting.data.repository.IMoviesRepository
import com.example.popcorntime.old_needs_sorting.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    }

    @Provides
    @Singleton
    fun getMovieRepo(moviesSource: IMoviesSource): IMoviesRepository =
        MoviesRepository(moviesSource = moviesSource)


    @Provides
    @Singleton
    fun getMoviesSource(movieAPI: MovieAPI, apiKey: String): IMoviesSource =
        MoviesSourceImpl(movieAPI = movieAPI, apiKey = apiKey)


    @Provides
    fun provideApiKey(): String {
        return BuildConfig.APIKEY
    }


    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): MovieAPI =
        retrofit.create(MovieAPI::class.java)


    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideConnectionUtil(context: Context): ConnectionUtil = ConnectionUtil(context)


    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}