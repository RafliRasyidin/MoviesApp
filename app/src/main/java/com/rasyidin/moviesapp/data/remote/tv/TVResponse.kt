package com.rasyidin.moviesapp.data.remote.tv

import com.google.gson.annotations.SerializedName

data class TVResponse(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: MutableList<TV>,
)