package com.paymob.movies.modules.listing.presentation

import com.paymob.movies.modules.listing.domain.entites.MovieEntity

sealed class MoviesListingState {
    data class Shimmer(var isShow: Boolean) : MoviesListingState()
    data class Loading(var isShow: Boolean) : MoviesListingState()
    data class Success(val movies: ArrayList<MovieEntity>) : MoviesListingState()

    data class WishlistError(val errorMessage: String) : MoviesListingState()

    data class ApiError(val date: String) : MoviesListingState()
    data object InternetError : MoviesListingState()
}