package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.model.Movies
import com.rasyidin.moviesapp.utils.DataDummy.generateDummyMovies

class MovieViewModel : ViewModel() {

    fun getMovies(): List<Movies> = generateDummyMovies()
}