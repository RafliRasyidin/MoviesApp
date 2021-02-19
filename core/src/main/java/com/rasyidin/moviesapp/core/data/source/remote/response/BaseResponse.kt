package com.rasyidin.moviesapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @field:SerializedName("results")
    val results: List<T>?
)