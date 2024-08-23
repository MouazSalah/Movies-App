package com.paymob.movies.modules.listing.data.datastore.local

import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity

interface IMoviesLocalDataSource {
    suspend fun loadMovies(): MoviesListingEntity?

    suspend fun insertMovies(movies: MoviesListingEntity)
}