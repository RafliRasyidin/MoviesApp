package com.rasyidin.moviesapp.data.remote.movies.detail

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    @field:SerializedName("genres")
    val genres: List<Genre>,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = "",

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)