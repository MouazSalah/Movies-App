package com.paymob.movies.modules.details.domain.entity

class MovieDetailsEntity (
    val id: Int?,
    val name: String?,
    val moviePoster: String?,
    val overview : String?,
    val releaseDate: String?,
    val genres: String?,
    val runtime: String?,
    val productionCompanies: String?,
    val productionCountries: String?,
    val status: String?,
    val rating: Double?,
    var isWishlist : Boolean = false
)