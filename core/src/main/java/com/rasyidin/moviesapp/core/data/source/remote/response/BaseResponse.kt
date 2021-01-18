package com.rasyidin.moviesapp.core.data.source.remote.response

data class BaseResponse<T>(

    val results: List<T>
)