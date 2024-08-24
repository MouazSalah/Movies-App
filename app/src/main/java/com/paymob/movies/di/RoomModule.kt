package com.paymob.movies.di

import android.content.Context
import androidx.room.Room
import com.paymob.movies.db.local.room.MoviesDao
import com.paymob.movies.db.local.room.MoviesRoomDatabase
import com.paymob.movies.modules.listing.data.datastore.local.IMoviesLocalDataSource
import com.paymob.movies.modules.listing.data.datastore.local.MoviesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    companion object {
        private const val DATA_BASE_NAME = "IMDB_Movies_database"
    }

    @Singleton
    @Provides
    fun provideMoviesDao(dataBase: MoviesRoomDatabase): MoviesDao = dataBase.movieDao()


    @Singleton
    @Provides
    fun provideLocalDataSource(movieDao : MoviesDao): IMoviesLocalDataSource = MoviesLocalDataSourceImpl(movieDao)


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MoviesRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesRoomDatabase::class.java,
            DATA_BASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}