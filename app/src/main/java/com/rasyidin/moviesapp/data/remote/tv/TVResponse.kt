package com.rasyidin.moviesapp.data.remote.tv

import com.google.gson.annotations.SerializedName
import com.rasyidin.moviesapp.data.local.entity.TV

data class TVResponse(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: List<TV>,
)