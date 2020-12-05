package com.rasyidin.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.data.vo.Resource


interface MovieCatalogueDataSource {

    fun getMovies(): LiveData<Resource<List<Movie>>>

    fun getTV(): LiveData<Resource<List<TV>>>

    fun getDetailMovie(id: Int?): LiveData<Resource<DetailMovieResponse>>

    fun getDetailTV(id: Int?): LiveData<Resource<DetailTVResponse>>

    fun getFavMovies(): LiveData<PagedList<Movie>>

    fun getFavTv(): LiveData<PagedList<TV>>

    suspend fun setFavMovie(movie: Movie)

    suspend fun setFavTv(tv: TV)

    suspend fun removeFavMovie(movie: Movie)

    suspend fun removeFavTv(tv: TV)

    suspend fun isFavoritedMovie(movie: Movie?): Boolean

    suspend fun isFavoritedTv(tv: TV?): Boolean
}