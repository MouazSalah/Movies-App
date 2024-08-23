package com.paymob.movies.modules.listing.data.datastore.remote

import com.paymob.movies.modules.listing.data.models.MoviesResponse
import com.paymob.movies.modules.listing.data.params.MoviesListingParams
import retrofit2.Response

interface IMoviesRemoteDataSource {

    suspend fun getMovies(params : MoviesListingParams) : Response<MoviesResponse>

}