package com.rasyidin.moviesapp.data.remote.tv.detail

import com.google.gson.annotations.SerializedName

data class DetailTVResponse(
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @field:SerializedName("genres")
    val genres: List<Genre>,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)