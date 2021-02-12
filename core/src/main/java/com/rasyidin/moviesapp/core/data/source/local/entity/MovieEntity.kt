package com.rasyidin.moviesapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favMovies")
data class MovieEntity(
    @PrimaryKey
    var id: Int? = null,
    var posterPath: String? = "",
    var backdropPath: String? = "",
    var overview: String? = "",
    var releaseDate: String? = "",
    var title: String? = "",
    var popularity: Double? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
    var isFavorite: Boolean = false

) : Parcelable