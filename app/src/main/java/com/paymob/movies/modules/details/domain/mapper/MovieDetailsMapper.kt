package com.paymob.movies.modules.details.domain.mapper

import com.paymob.movies.modules.details.data.models.MovieDetailsResponse
import com.paymob.movies.modules.details.domain.entity.MovieDetailsEntity

object MovieDetailsMapper {

    fun mapMovieDetailsToEntity(response : MovieDetailsResponse, moviesIds : List<String>) : MovieDetailsEntity {
        return MovieDetailsEntity(
            id = response.id,
            name = response.originalTitle,
            moviePoster = response.posterPath,
            overview = response.overview,
            releaseDate = response.releaseDate,
            runtime = response.runtime.toString(),
            productionCompanies = response.productionCompanies?.filterNotNull()
                ?.map { it.name }
                ?.joinToString(", ")
                ?: "",

            productionCountries = response.productionCountries?.filterNotNull()
                ?.map { it.name }
                ?.joinToString(", ")
                ?: "",
            status = response.status,
            genres = response.genres?.filterNotNull()
                ?.map { it.name }
                ?.joinToString(", ")
                ?: "",
            rating = 3.5,
            isWishlist = moviesIds.contains(response.id.toString())
        )
    }
}