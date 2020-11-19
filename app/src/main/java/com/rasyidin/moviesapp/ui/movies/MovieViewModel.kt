package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.data.remote.movies.Movie
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository

class MovieViewModel(private val repository: RemoteRepository) : ViewModel() {

    fun getMovies(): LiveData<List<Movie>> = repository.getMovies()

}