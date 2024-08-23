package com.paymob.movies.modules.listing.domain.repository

import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity

interface IMoviesRepository {

    suspend fun getMovies(params: MoviesListingParams): ApiResult<MoviesListingEntity>
}
