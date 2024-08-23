package com.paymob.movies.modules.details.presentation

import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity

sealed class MovieDetailsState {
    data class Shimmer(var isShow: Boolean) : MovieDetailsState()
    data class Loading(var isShow: Boolean) : MovieDetailsState()
    data class Success(val movieDetails: MovieDetailsEntity) : MovieDetailsState()

    data class ApiError(val date: String) : MovieDetailsState()
    data object InternetError : MovieDetailsState()
}