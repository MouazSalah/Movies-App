package com.paymob.movies.modules.details.data.repository

import com.paymob.movies.core.BaseApp
import com.paymob.movies.extesnion.isNetworkAvailable
import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.details.data.api.MovieDetailsWebServices
import com.paymob.movies.modules.details.data.mapper.MovieDetailsMapper
import com.paymob.movies.modules.details.data.params.MovieDetailsParams
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity
import com.paymob.movies.modules.details.domain.repository.IMovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiInterface: MovieDetailsWebServices
) : IMovieDetailsRepository {

    override suspend fun getMovieDetails(params: MovieDetailsParams): ApiResult<MovieDetailsEntity> {
        return if (isNetworkAvailable(BaseApp.instance.applicationContext)) {
            try {
                val response = apiInterface.getMovieDetails(params.movieId.toString())

                if (response.isSuccessful) {
                    response.body()?.let { movieDetailsResponse ->
                        val moviesEntities : MovieDetailsEntity = MovieDetailsMapper.mapMovieDetailsToEntity(movieDetailsResponse)
                        ApiResult.Success(moviesEntities)
                    } ?: run {
                        ApiResult.ApiError("${response.code()} - ${response.message()}")
                    }
                } else {
                    ApiResult.ApiError("${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                ApiResult.ApiError(e.message.toString())
            }
        } else {
            ApiResult.InternetError("No internet connection")
        }
    }
}