package com.paymob.movies.modules.listing.domain.entites

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {

    @TypeConverter
    fun fromMovieList(movies: List<MovieEntity>): String {
        return Gson().toJson(movies)
    }

    @TypeConverter
    fun toMovieList(moviesJson: String): List<MovieEntity> {
        val type = object : TypeToken<List<MovieEntity>>() {}.type
        return Gson().fromJson(moviesJson, type)
    }
}
