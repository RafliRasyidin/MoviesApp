package com.rasyidin.moviesapp.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface IMovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    suspend fun getDetailMovie(id: Int?): Flow<Movie>

    suspend fun searchMovies(querySearch: String?): Flow<List<Movie>>

    fun getFavMovies(): LiveData<PagedList<Movie>>

    fun setFavMovie(movie: Movie, state: Boolean)

    fun getDetailMovieByIdFromDb(movieId: Int?): Flow<Movie>


}