package com.paymob.movies.modules.details.domain.usecases

import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.details.data.params.MovieDetailsParams
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity
import com.paymob.movies.modules.details.domain.repository.IMovieDetailsRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: IMovieDetailsRepository) {
    suspend operator fun invoke(params: MovieDetailsParams): ApiResult<MovieDetailsEntity> {
        return repository.getMovieDetails(params)
    }
}