package com.rasyidin.moviesapp.data.remote.movies

import com.google.gson.annotations.SerializedName
import com.rasyidin.moviesapp.data.local.entity.Movie

data class MoviesResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: List<Movie>

)