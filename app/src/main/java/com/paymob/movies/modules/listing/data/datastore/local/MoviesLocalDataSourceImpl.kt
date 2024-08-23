package com.paymob.movies.modules.listing.data.datastore.local

import com.paymob.movies.db.local.room.MoviesDao
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(private val moviesDao: MoviesDao) :
    IMoviesLocalDataSource {

    override suspend fun loadMovies(): MoviesListingEntity? = moviesDao.getMovies()

    override suspend fun insertMovies(movies: MoviesListingEntity) = moviesDao.insertMovies(movies)
}