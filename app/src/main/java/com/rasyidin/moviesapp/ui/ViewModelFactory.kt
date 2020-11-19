package com.rasyidin.moviesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository
import com.rasyidin.moviesapp.ui.detail.DetailViewModel
import com.rasyidin.moviesapp.ui.movies.MovieViewModel
import com.rasyidin.moviesapp.ui.tv.TvViewModel

class ViewModelFactory(
    private val repository: RemoteRepository
) : ViewModelProvider.NewInstanceFactory() {

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
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }

    }
}