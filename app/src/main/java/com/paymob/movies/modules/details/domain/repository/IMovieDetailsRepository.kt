package com.paymob.movies.modules.details.domain.repository

import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.details.data.params.MovieDetailsParams
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity

interface IMovieDetailsRepository {
    suspend fun getMovieDetails(params: MovieDetailsParams): ApiResult<MovieDetailsEntity>
}
