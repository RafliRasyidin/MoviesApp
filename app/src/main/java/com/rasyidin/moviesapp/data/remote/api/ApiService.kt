package com.rasyidin.moviesapp.data.remote.api

import com.rasyidin.moviesapp.data.remote.movies.MoviesResponse
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.TVResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.utils.ConstantValue.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key")
        api: String = API_KEY
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id")
        id: Int?,
        @Query("api_key")
        api: String = API_KEY
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getTV(
        @Query("api_key")
        api: String = API_KEY
    ): Call<TVResponse>

    @GET("tv/{tv_id}")
    fun getTVDetail(
        @Path("tv_id")
        id: Int?,
        @Query("api_key")
        api: String = API_KEY
    ): Call<DetailTVResponse>

}