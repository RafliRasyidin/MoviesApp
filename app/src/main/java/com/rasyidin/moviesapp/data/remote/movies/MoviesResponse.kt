package com.rasyidin.moviesapp.data.remote.movies

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: MutableList<Movie>,

    )