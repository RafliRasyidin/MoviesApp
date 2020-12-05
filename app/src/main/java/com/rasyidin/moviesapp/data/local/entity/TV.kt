package com.rasyidin.moviesapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favTV")
@Parcelize
data class TV(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
) : Parcelable