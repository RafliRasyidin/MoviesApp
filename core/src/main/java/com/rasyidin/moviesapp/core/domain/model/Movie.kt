package com.rasyidin.moviesapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val isFavorite: Boolean
) : Parcelable