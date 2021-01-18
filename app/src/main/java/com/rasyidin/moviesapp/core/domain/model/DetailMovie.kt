package com.rasyidin.moviesapp.core.domain.model

data class DetailMovie(
    val backdropPath: String?,
    val genres: List<Genre>,
    val id: Int?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)