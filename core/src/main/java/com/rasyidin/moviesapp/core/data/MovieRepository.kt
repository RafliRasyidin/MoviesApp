package com.rasyidin.moviesapp.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.source.local.MovieLocalDataSource
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.MoviesResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.source.MoviesDataSource
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.repository.IMovieRepository
import com.rasyidin.moviesapp.core.utils.AppExecutors
import com.rasyidin.moviesapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MoviesDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MoviesResponse>>() {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapMovieEntitiesToMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val movies = DataMapper.mapMoviesResponseToEntities(data)
                localDataSource.insertMovie(movies)
            }
        }.asFlow()

    override suspend fun searchMovies(querySearch: String?): Resource<List<Movie>> {
        return when (val apiResponse = remoteDataSource.searchMovies(querySearch).first()) {
            is ApiResponse.Success -> {
                val result = DataMapper.mapMoviesResponseToMovies(apiResponse.data)
                Resource.Success(result)
            }
            is ApiResponse.Empty -> {
                Resource.Error(null, apiResponse.toString())
            }
            is ApiResponse.Error -> {
                Resource.Error(null, apiResponse.errorMessage)
            }
        }
    }

    override fun getFavMovies(): LiveData<PagedList<Movie>> {
        val data = localDataSource.getFavMovies().map {
            DataMapper.mapMovieEntityToMovie(it)
        }
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(data, config).build()
    }

    override fun setFavMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}