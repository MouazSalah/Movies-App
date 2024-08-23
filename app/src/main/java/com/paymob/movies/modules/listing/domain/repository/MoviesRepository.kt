package com.paymob.movies.modules.listing.domain.repository

import com.paymob.movies.core.BaseApp
import com.paymob.movies.extesnion.isNetworkAvailable
import com.paymob.movies.db.api.ApiResult
import com.paymob.movies.modules.listing.data.datastore.local.IMoviesLocalDataSource
import com.paymob.movies.modules.listing.data.datastore.remote.IMoviesRemoteDataSource
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity
import com.paymob.movies.modules.listing.domain.mapper.MoviesListingMapper
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val localDataSource: IMoviesLocalDataSource,
    private val remoteDataSource: IMoviesRemoteDataSource
) : IMoviesRepository {


    private suspend fun insertMoviesToLocalStorage(movies: MoviesListingEntity) {
        localDataSource.insertMovies(movies)
    }

    private suspend fun getMoviesFromLocalStorage(errorMessage: String): ApiResult<MoviesListingEntity> {
        val movieEntity = localDataSource.loadMovies()
        return if (movieEntity != null && movieEntity.movies.isNotEmpty()) {
            ApiResult.Success(movieEntity)
        } else {
            ApiResult.InternetError(errorMessage)
        }
    }

    override suspend fun getMovies(params: MoviesListingParams): ApiResult<MoviesListingEntity> {
        return if (isNetworkAvailable(BaseApp.instance.applicationContext)) {
            try {
                val response = remoteDataSource.getMovies(params)

                if (response.isSuccessful) {
                    response.body()?.let { moviesResponse ->
                        val moviesEntities : MoviesListingEntity = MoviesListingMapper.mapMoviesResponseToEntities(moviesResponse)
                        insertMoviesToLocalStorage(moviesEntities)
                        ApiResult.Success(moviesEntities)
                    } ?: run {
                        getMoviesFromLocalStorage(errorMessage = "${response.code()} - ${response.message()}")
                    }
                } else {
                    getMoviesFromLocalStorage(errorMessage = "${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                getMoviesFromLocalStorage(errorMessage = e.message.toString())
            }
        } else {
            getMoviesFromLocalStorage(errorMessage = "No internet connection")
        }
    }
}