package com.rasyidin.moviesapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favMovies")
@Parcelize
data class Movie(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
) : Parcelable