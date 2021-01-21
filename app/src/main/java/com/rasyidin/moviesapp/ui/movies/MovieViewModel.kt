package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.*
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>> = _search

    private fun setLoading() = _loading.postValue(true)

    private fun finishLoading() = _loading.postValue(false)

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        movieUseCase.getPopularMovies().asLiveData()

    fun searchMovies(querySearch: String?) {
        viewModelScope.launch {
            movieUseCase.searchMovies(querySearch).onStart {
                setLoading()
            }.collect {
                _search.postValue(it)
                finishLoading()
            }
        }
    }

}