package com.rasyidin.moviesapp.data.vo

data class Resource<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T?): Resource<T> = Resource(StatusResponse.SUCCESS, body, null)

        fun <T> loading(body: T?): Resource<T> = Resource(StatusResponse.LOADING, body, null)

        fun <T> error(message: String?, body: T?): Resource<T> =
            Resource(StatusResponse.ERROR, body, message)
    }
}