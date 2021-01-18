package com.rasyidin.moviesapp.core.data.source.remote

import com.rasyidin.moviesapp.core.data.source.remote.response.movies.source.MoviesDataSource
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.source.TvDataSource

class RemoteDataSource(
    private val moviesDataSource: MoviesDataSource,
    private val tvDataSource: TvDataSource
) {

    /*suspend fun getPopularMovies() = moviesDataSource.getPopularMovies()

    suspend fun getDetailMovie(movieId: Int?) = moviesDataSource.getDetailMovie(movieId)

    suspend fun searchMovies(querySearch: String?) = moviesDataSource.searchMovies(querySearch)

    fun getPopularTv() = tvDataSource.getPopularTv()

    fun getDetailTv(tvId: Int?) = tvDataSource.getDetailTv(tvId)

    fun searchTv(querySearch: String?) = tvDataSource.searchTv(querySearch)*/

}