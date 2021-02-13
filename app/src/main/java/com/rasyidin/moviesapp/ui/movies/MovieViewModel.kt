package com.rasyidin.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        movieUseCase.getPopularMovies().asLiveData()

    private var debounceDuration: Long = 300

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchMovies = queryChannel.asFlow()
        .debounce(debounceDuration)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            movieUseCase.searchMovies(it)
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun shouldDebounce(state: Boolean) {
        debounceDuration = if (state) 3000 else 300
    }

}