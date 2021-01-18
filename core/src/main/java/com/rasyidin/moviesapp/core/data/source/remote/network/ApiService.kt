package com.rasyidin.moviesapp.core.data.source.remote.network

import com.rasyidin.moviesapp.core.data.source.remote.response.BaseResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.MoviesResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.TVResponse
import com.rasyidin.moviesapp.core.utils.ConstantValue.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        api: String = API_KEY
    ): BaseResponse<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id")
        id: Int?,
        @Query("api_key")
        api: String = API_KEY
    ): MoviesResponse

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("api_key")
        api: String = API_KEY
    ): BaseResponse<TVResponse>

    @GET("tv/{tv_id}")
    suspend fun getTVDetail(
        @Path("tv_id")
        id: Int?,
        @Query("api_key")
        api: String = API_KEY
    ): TVResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query")
        querySearch: String? = "",
        @Query("api_key")
        api: String = API_KEY
    ): BaseResponse<MoviesResponse>

    @GET("search/tv")
    suspend fun searchTv(
        @Query("query")
        querySearch: String? = "",
        @Query("api_key")
        api: String = API_KEY
    ): BaseResponse<TVResponse>

}