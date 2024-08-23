package com.paymob.movies.modules.listing.data.params

import com.google.gson.annotations.SerializedName
import com.paymob.movies.core.HashMapParams
import com.paymob.movies.modules.listing.domain.enum.MoviesCategory

data class MoviesListingParams(
    @SerializedName("category")
    var category: MoviesCategory? = MoviesCategory.POPULAR,

    @SerializedName("language")
    var language: String? = "en-US",

    @SerializedName("page")
    var pageIndex: Int ?= 1

    ) : HashMapParams {
    override fun dataClass(): Any = this
}