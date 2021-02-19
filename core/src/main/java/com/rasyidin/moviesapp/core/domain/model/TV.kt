package com.rasyidin.moviesapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TV(
    val id: Int?,
    val firstAirDate: String?,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double?,
    val isFavorite: Boolean = false
) : Parcelable