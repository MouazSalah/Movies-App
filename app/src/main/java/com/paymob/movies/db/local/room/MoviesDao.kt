package com.paymob.movies.db.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: MoviesListingEntity)

    @Query("SELECT * FROM movies_listing")
    suspend fun getMovies(): MoviesListingEntity?

    @Query("DELETE FROM movies_listing")
    suspend fun clearAll()
}