package com.paymob.movies.navigation

enum class MoviesScreens {
    MoviesListingScreen,
    MoviesDetailsScreen;

    companion object  {

        fun fromRoute(routeName : String) : MoviesScreens {
            return when(routeName.substringBefore("/")){
                MoviesListingScreen.name -> MoviesListingScreen
                MoviesDetailsScreen.name -> MoviesDetailsScreen
                else -> {
                    throw  IllegalArgumentException("Route $routeName doesn't exist")
                }
            }
        }
    }
}