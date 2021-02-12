package com.rasyidin.moviesapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favTV")
data class TVEntity(
    @PrimaryKey
    var id: Int? = null,

    var firstAirDate: String? = "",

    @ColumnInfo(name = "title")
    var name: String? = "",

    var posterPath: String? = "",

    var backdropPath: String? = "",

    var overview: String? = "",

    var voteAverage: Double? = null,

    var voteCount: Int? = null,

    var popularity: Double? = null,

    var isFavorite: Boolean = false
) : Parcelable