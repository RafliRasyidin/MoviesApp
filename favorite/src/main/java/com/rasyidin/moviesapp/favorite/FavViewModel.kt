package com.rasyidin.moviesapp.favorite

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase

class FavViewModel(private val movieUseCase: MovieUseCase, private val tvUseCase: TVUseCase) :
    ViewModel() {

    val favoriteMovie = movieUseCase.getFavMovies()

    val favoriteTv = tvUseCase.getFavTv()

    fun setFavMovie(movie: Movie, state: Boolean) = movieUseCase.setFavMovie(movie, state)

    fun setFavTv(tv: TV, state: Boolean) = tvUseCase.setFavTv(tv, state)
}