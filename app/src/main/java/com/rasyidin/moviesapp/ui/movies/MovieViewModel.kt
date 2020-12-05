package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import com.rasyidin.moviesapp.data.vo.Resource

class MovieViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> = repository.getMovies()

}