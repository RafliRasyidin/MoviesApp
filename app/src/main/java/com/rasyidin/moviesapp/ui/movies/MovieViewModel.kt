package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        movieUseCase.getPopularMovies().asLiveData()

    suspend fun searchMovies(querySearch: String?) = movieUseCase.searchMovies(querySearch)

}