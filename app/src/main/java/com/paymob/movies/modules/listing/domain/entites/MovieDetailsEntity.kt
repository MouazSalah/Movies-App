package com.paymob.movies.modules.listing.domain.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val rating: Double
)