package com.rasyidin.moviesapp.core.data.source.remote.response.movies

import com.google.gson.annotations.SerializedName
import com.rasyidin.moviesapp.core.domain.model.Genre

data class MoviesResponse(

    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    val overview: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    val title: String? = "",

    val genres: List<Genre>,

    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,
)