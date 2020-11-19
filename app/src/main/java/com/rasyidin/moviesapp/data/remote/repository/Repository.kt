package com.rasyidin.moviesapp.data.remote.repository

import androidx.lifecycle.LiveData
import com.rasyidin.moviesapp.data.remote.movies.Movie
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.TV
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse


interface Repository {

    fun getMovies(): LiveData<List<Movie>>

    fun getTV(): LiveData<List<TV>>

    fun getDetailMovie(id: Int?): LiveData<DetailMovieResponse>

    fun getDetailTV(id: Int?): LiveData<DetailTVResponse>
}