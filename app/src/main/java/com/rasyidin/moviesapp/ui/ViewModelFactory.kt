package com.rasyidin.moviesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import com.rasyidin.moviesapp.ui.detail.DetailViewModel
import com.rasyidin.moviesapp.ui.fav.FavViewModel
import com.rasyidin.moviesapp.ui.movies.MovieViewModel
import com.rasyidin.moviesapp.ui.tv.TvViewModel

class ViewModelFactory(
    private val repository: MovieCatalogueRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavViewModel::class.java) -> {
                FavViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }

    }
}