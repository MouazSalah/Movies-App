package com.paymob.movies.modules.details.data.api

import com.paymob.movies.modules.details.data.models.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieDetailsWebServices {

    companion object {
        private const val GET_MOVIE_DETAILS = "{movieId}" // category can be ( popular - top_rated - upcoming - now_playing )
    }

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movieId") movieId : String): Response<MovieDetailsResponse>
}