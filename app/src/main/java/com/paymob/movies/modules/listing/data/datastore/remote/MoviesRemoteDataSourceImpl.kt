package com.paymob.movies.modules.listing.data.datastore.remote

import com.paymob.movies.extesnion.toHashMapParams
import com.paymob.movies.modules.listing.data.api.MoviesWebServices
import com.paymob.movies.modules.listing.data.models.MoviesResponse
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(private val apiInterface : MoviesWebServices) :
    IMoviesRemoteDataSource {

    override suspend fun getMovies(params: MoviesListingParams): Response<MoviesResponse> {
        val category = params.category?.categoryName.toString().lowercase()
        params.category = null;
        val result = apiInterface.getMovies(category = category, params = params.toHashMapParams()!!)
        return result
    }
}