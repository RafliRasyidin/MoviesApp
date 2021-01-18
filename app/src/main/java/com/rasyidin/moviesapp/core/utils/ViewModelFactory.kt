package com.rasyidin.moviesapp.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase
import com.rasyidin.moviesapp.ui.detail.DetailViewModel
import com.rasyidin.moviesapp.ui.fav.FavViewModel
import com.rasyidin.moviesapp.ui.movies.MovieViewModel
import com.rasyidin.moviesapp.ui.tv.TvViewModel

class ViewModelFactory(
    private val movieUseCase: MovieUseCase,
    private val tvUseCase: TVUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(tvUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieUseCase, tvUseCase) as T
            }
            modelClass.isAssignableFrom(FavViewModel::class.java) -> {
                FavViewModel(movieUseCase, tvUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }

    }
}