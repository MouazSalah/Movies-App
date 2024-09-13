package com.paymob.movies.navigation

import MovieDetailsComposeScreen
import MoviesListingComposeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MoviesNavigation(){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = MoviesScreens.MoviesListingScreen.name, builder = {
        composable(MoviesScreens.MoviesListingScreen.name) {
            MoviesListingComposeScreen(navController)
        }
        composable(MoviesScreens.MoviesDetailsScreen.name) {
            MovieDetailsComposeScreen()
        }
    })
}