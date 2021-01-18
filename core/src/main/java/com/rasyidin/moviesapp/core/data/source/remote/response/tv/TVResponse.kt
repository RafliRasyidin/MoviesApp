package com.rasyidin.moviesapp.core.data.source.remote.response.tv

import com.google.gson.annotations.SerializedName
import com.rasyidin.moviesapp.core.domain.model.Genre

data class TVResponse(
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    val name: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    val overview: String? = "",

    val genres: List<Genre>,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    val popularity: Double? = null,
)