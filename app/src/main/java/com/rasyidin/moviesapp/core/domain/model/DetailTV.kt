package com.rasyidin.moviesapp.core.domain.model

data class DetailTV(
    val backdropPath: String?,
    val firstAirDate: String?,
    val genres: List<Genre>,
    val id: Int?,
    val name: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)