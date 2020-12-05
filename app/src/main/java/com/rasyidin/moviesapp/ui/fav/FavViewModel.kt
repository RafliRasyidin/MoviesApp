package com.rasyidin.moviesapp.ui.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {

    fun getFavMovies(): LiveData<PagedList<Movie>> = repository.getFavMovies()

    fun getFavTv(): LiveData<PagedList<TV>> = repository.getFavTv()

    fun removeFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavMovie(movie)
        }
    }

    fun removeFavTv(tv: TV) {
        viewModelScope.launch(Dispatchers.IO) { repository.removeFavTv(tv) }
    }

    fun setFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) { repository.setFavMovie(movie) }
    }

    fun setFavTv(tv: TV) {
        viewModelScope.launch(Dispatchers.IO) { repository.setFavTv(tv) }
    }
}