package com.paymob.movies.modules.listing.data.api

import com.paymob.movies.modules.listing.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.*

interface MoviesWebServices {

    companion object {
        private const val GET_ALL_MOVIES = "{category}" // category can be ( popular - top_rated - upcoming - now_playing )
    }

    @GET(GET_ALL_MOVIES)
    suspend fun getMovies(@Path("category") category : String, @QueryMap params: HashMap<String, String?>): Response<MoviesResponse>
}