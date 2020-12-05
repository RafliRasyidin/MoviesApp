package com.rasyidin.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {

    fun getDetailMovie(movieId: Int?) = repository.getDetailMovie(movieId)

    fun getDetailTv(tvId: Int?) = repository.getDetailTV(tvId)

    fun setFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) { repository.setFavMovie(movie) }
    }

    fun setFavTv(tv: TV) {
        viewModelScope.launch(Dispatchers.IO) { repository.setFavTv(tv) }
    }

    fun removeFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavMovie(movie)
        }
    }

    fun removeFavTv(tv: TV) {
        viewModelScope.launch(Dispatchers.IO) { repository.removeFavTv(tv) }
    }

    fun isFavoritedMovie(movie: Movie?): Boolean =
        runBlocking {
            repository.isFavoritedMovie(movie)
        }

    fun isFavoritedTv(tv: TV?): Boolean =
        runBlocking {
            repository.isFavoritedTv(tv)
        }

}
