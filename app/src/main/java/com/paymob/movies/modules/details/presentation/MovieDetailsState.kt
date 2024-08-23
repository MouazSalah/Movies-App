package com.paymob.movies.modules.details.presentation

import com.paymob.movies.modules.listing.domain.entites.MovieEntity

sealed class MovieDetailsState {
    data class Shimmer(var isShow: Boolean) : MovieDetailsState()
    data class Loading(var isShow: Boolean) : MovieDetailsState()
    data class Success(val movies: ArrayList<MovieEntity>) : MovieDetailsState()

    data class ApiError(val date: String) : MovieDetailsState()
    data object InternetError : MovieDetailsState()
}