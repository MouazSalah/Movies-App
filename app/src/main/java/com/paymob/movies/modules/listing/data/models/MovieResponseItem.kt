package com.paymob.movies.modules.listing.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponseItem(

    @field:SerializedName("gender")
	val gender: Int? = null,

    @field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

    @field:SerializedName("original_name")
	val originalName: String? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,

    @field:SerializedName("known_for")
	val knownFor: List<KnownForItem?>? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("original_title")
	val originalTitle: String? = null,

    @field:SerializedName("release_date")
	val releaseDate: String? = null,

    @field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("vote_average")
	val voteAverage: Float? = null,

    @field:SerializedName("adult")
	val adult: Boolean? = null
)