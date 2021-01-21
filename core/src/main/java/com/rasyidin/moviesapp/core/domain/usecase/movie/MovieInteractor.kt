package com.rasyidin.moviesapp.core.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: IMovieRepository) :
    MovieUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        repository.getPopularMovies()

    override suspend fun getDetailMovie(id: Int?): Flow<Movie> =
        repository.getDetailMovie(id)

    override suspend fun searchMovies(querySearch: String?): Flow<List<Movie>> =
        repository.searchMovies(querySearch)

    override fun getFavMovies(): LiveData<PagedList<Movie>> =
        repository.getFavMovies()

    override fun setFavMovie(movie: Movie, state: Boolean) {
        repository.setFavMovie(movie, state)
    }

}