package com.rasyidin.moviesapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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
) : Serializable