package com.rasyidin.moviesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase, private val tvUseCase: TVUseCase) :
    ViewModel() {

    private val _detailMovie = MutableLiveData<Movie>()
    val detailMovie: LiveData<Movie> get() = _detailMovie

    private val _detailTv = MutableLiveData<TV>()
    val detailTv: LiveData<TV> get() = _detailTv

    fun getDetailMovie(movieId: Int?) {
        viewModelScope.launch {
            movieUseCase.getDetailMovie(movieId)
                .collect {
                    _detailMovie.postValue(it)
                }
        }
    }

    fun getDetailTv(tvId: Int?) {
        viewModelScope.launch {
            tvUseCase.getDetailTV(tvId)
                .collect {
                    _detailTv.postValue(it)
                }
        }
    }

    fun getDetailMovieByIdFromDb(movieId: Int?) {
        viewModelScope.launch {
            movieUseCase.getDetailMovieByIdFromDb(movieId)
                .collect {
                    _detailMovie.postValue(it)
                }
        }
    }

    fun setFavMovie(movie: Movie, state: Boolean) = movieUseCase.setFavMovie(movie, state)

    fun setFavTv(tv: TV, state: Boolean) = tvUseCase.setFavTv(tv, state)

}
