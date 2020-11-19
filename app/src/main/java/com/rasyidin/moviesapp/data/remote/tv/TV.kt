package com.rasyidin.moviesapp.data.remote.tv

import com.google.gson.annotations.SerializedName

data class TV(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)