package com.paymob.movies.modules.listing.domain.usecases

import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity
import com.paymob.movies.modules.listing.domain.repository.IMoviesRepository
import javax.inject.Inject

class MoviesListingUseCase @Inject constructor(private val repository: IMoviesRepository) {
    suspend operator fun invoke(params: MoviesListingParams): ApiResult<MoviesListingEntity> {
        return repository.getMovies(params)
    }
}