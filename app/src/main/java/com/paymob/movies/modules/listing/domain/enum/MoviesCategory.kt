package com.paymob.movies.modules.listing.domain.enum

enum class MoviesCategory (var categoryName : String ) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    NOW_PLAYING("now_playing")
}