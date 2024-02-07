package com.example.popcorntime.core.dependencyinjection.modules


import android.app.Application
import android.content.Context
import com.example.popcorntime.BuildConfig
import com.example.popcorntime.core.helpers.MovieAPI
import com.example.popcorntime.core.utils.ConnectionUtil
import com.example.popcorntime.data.datasource.IMoviesSource
import com.example.popcorntime.data.datasource.MoviesSource
import com.example.popcorntime.data.repository.IMoviesRepository
import com.example.popcorntime.data.repository.MoviesRepository
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
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val CACHE_SIZE: Long = 10 * 1024 * 1024


    @Provides
    @Singleton
    fun getMovieRepo(moviesSource: IMoviesSource): IMoviesRepository =
        MoviesRepository(moviesSource = moviesSource)


    @Provides
    @Singleton
    fun getMoviesSource(movieAPI: MovieAPI, apiKey: String): IMoviesSource =
        MoviesSource(movieAPI = movieAPI, apiKey = apiKey)


    @Provides
    fun provideApiKey(): String {
        return BuildConfig.APIKEY
    }


    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun getRetrofit(cache: Cache, interceptor: HttpLoggingInterceptor): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).cache(cache).build())
            .build()


    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }


    @Provides
    @Singleton
    fun provideConnectionUtil(context: Context): ConnectionUtil = ConnectionUtil(context)


    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}