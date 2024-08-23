package com.paymob.movies.db.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paymob.movies.modules.listing.domain.entites.MovieTypeConverter
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity

@Database(entities = [MoviesListingEntity::class], version = 1, exportSchema = false)
@TypeConverters(MovieTypeConverter::class)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}