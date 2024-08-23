package com.paymob.movies.di

import com.paymob.movies.db.local.datastore.DataStoreManager
import com.paymob.movies.modules.details.data.api.MovieDetailsWebServices
import com.paymob.movies.modules.details.data.repository.MovieDetailsRepositoryImpl
import com.paymob.movies.modules.details.domain.repository.IMovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MovieDetailsModule {

    @Singleton
    @Provides
    fun provideMovieDetailsWebService(@DIAnnotation.MoviesRetrofit retrofit: Retrofit): MovieDetailsWebServices = retrofit.create(
        MovieDetailsWebServices::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(apiInterface: MovieDetailsWebServices, dataStoreManager: DataStoreManager
    ) : IMovieDetailsRepository = MovieDetailsRepositoryImpl(apiInterface, dataStoreManager)
}