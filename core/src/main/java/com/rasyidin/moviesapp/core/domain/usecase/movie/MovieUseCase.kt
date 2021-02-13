package com.rasyidin.moviesapp.core.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    suspend fun searchMovies(querySearch: String?): Resource<List<Movie>>

    fun getFavMovies(): LiveData<PagedList<Movie>>

    fun setFavMovie(movie: Movie, state: Boolean)

}