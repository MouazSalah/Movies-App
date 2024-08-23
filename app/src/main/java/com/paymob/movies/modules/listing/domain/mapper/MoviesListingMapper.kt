package com.paymob.movies.modules.listing.domain.mapper

import com.paymob.movies.modules.listing.data.models.MovieResponseItem
import com.paymob.movies.modules.listing.data.models.MoviesResponse
import com.paymob.movies.modules.listing.domain.entites.MovieEntity
import com.paymob.movies.modules.listing.domain.entites.MoviesListingEntity

object MoviesListingMapper {

    fun mapMoviesResponseToEntities(response : MoviesResponse) : MoviesListingEntity {
        val entity = MoviesListingEntity()

        val items : ArrayList<MovieEntity> = mapMoviesToEntities(response.movies);

        entity.pageIndex = response.page
        entity.movies = items
        entity.hasNextPage = (response.page ?: 1) > 1
        entity.hasPreviousPage = (response.totalPages ?: 1) > 1
                                 && (response.page ?: 1) < (response.totalPages ?: 1)

        return entity
    }

    private fun mapMoviesToEntities(items: List<MovieResponseItem?>?) : ArrayList<MovieEntity> {
        val entities = arrayListOf<MovieEntity>()
        items?.forEach { model ->
            val item = MovieEntity(
                id = model?.id ?: 0,
                movieName = model?.originalTitle ?: "",
                moviePoster = model?.posterPath ?: "",
                rating = model?.voteAverage ?: 5f,
                releaseDate = model?.releaseDate ?: "",
                isFavorite = false,
            )

            entities.add(item);
        }

        return entities
    }
}