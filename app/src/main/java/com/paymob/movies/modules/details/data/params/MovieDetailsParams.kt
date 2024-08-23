package com.paymob.movies.modules.details.data.params

import com.google.gson.annotations.SerializedName
import com.paymob.movies.core.HashMapParams

data class MovieDetailsParams(

    @SerializedName("movieId")
    var movieId: Int? = null
) : HashMapParams {
    override fun dataClass(): Any = this
}