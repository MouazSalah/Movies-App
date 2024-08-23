package com.paymob.movies.di

import com.paymob.movies.db.local.room.MoviesDao
import com.paymob.movies.modules.listing.data.api.MoviesWebServices
import com.paymob.movies.modules.listing.data.datastore.local.IMoviesLocalDataSource
import com.paymob.movies.modules.listing.data.datastore.local.MoviesLocalDataSourceImpl
import com.paymob.movies.modules.listing.data.datastore.remote.IMoviesRemoteDataSource
import com.paymob.movies.modules.listing.data.datastore.remote.MoviesRemoteDataSourceImpl
import com.paymob.movies.modules.listing.domain.repository.IMoviesRepository
import com.paymob.movies.modules.listing.domain.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: MoviesWebServices): IMoviesRemoteDataSource = MoviesRemoteDataSourceImpl(apiInterface)


    @Singleton
    @Provides
    fun provideLocalDataSource(movieDao: MoviesDao): IMoviesLocalDataSource = MoviesLocalDataSourceImpl(movieDao)


    @Singleton
    @Provides
    fun provideMoviesRepository(localDataSource: MoviesLocalDataSourceImpl,
                                remoteDataSource: MoviesRemoteDataSourceImpl
    ) :
            IMoviesRepository = MoviesRepositoryImpl(localDataSource, remoteDataSource)
}