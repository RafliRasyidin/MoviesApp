package com.rasyidin.moviesapp.core.data.source.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TVResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    @field:SerializedName("overview")
    val overview: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,
)