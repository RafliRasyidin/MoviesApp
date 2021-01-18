package com.rasyidin.moviesapp.core.domain.model

import java.io.Serializable

data class Movie(
    val id: Int?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?,
    val genres: List<Genre> = emptyList(),
    val popularity: Double?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val isFavorite: Boolean = false
) : Serializable