package com.rasyidin.moviesapp.core.data.source.local

import androidx.paging.DataSource
import com.rasyidin.moviesapp.core.data.source.local.entity.MovieEntity
import com.rasyidin.moviesapp.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(
    private val movieDao: MoviesDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavMovies()

    suspend fun insertMovie(movieEntity: List<MovieEntity>) =
        movieDao.insertMovie(movieEntity)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.upsertFavMovie(movieEntity)
    }

}