package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository

class TvViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {

    fun getTv() = repository.getTV()

}