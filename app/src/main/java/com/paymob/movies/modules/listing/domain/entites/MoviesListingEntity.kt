package com.paymob.movies.modules.listing.domain.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "movies_listing")
@TypeConverters(MovieTypeConverter::class)
data class MoviesListingEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var pageIndex: Int? = null,
    var hasNextPage: Boolean? = null,
    var hasPreviousPage: Boolean? = null,
    var movies: List<MovieEntity> = arrayListOf(),
)

data class MovieEntity(
    val id: Int,
    val movieName: String,
    val moviePoster: String,
    val releaseDate: String,
    val rating: Float,
    var isFavorite : Boolean,
)