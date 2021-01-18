package com.rasyidin.moviesapp.core.domain.model

import java.io.Serializable

data class TV(
    val id: Int?,
    val firstAirDate: String?,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val genres: List<Genre> = emptyList(),
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double?,
    val isFavorite: Boolean = false
) : Serializable