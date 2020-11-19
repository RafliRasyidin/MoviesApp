package com.rasyidin.moviesapp.data.remote.movies

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
)