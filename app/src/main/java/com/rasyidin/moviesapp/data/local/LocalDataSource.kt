package com.rasyidin.moviesapp.data.local

import androidx.paging.DataSource
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.local.room.MoviesDatabase

class LocalDataSource(
    private val db: MoviesDatabase
) {

    fun getFavMovies(): DataSource.Factory<Int, Movie> = db.getMoviesDao().getFavMovies()

    fun getFavTv(): DataSource.Factory<Int, TV> = db.getTvDao().getFavTV()

    suspend fun setFavTv(tv: TV) = db.getTvDao().setFavTV(tv)

    suspend fun setFavMovie(movie: Movie) = db.getMoviesDao().setFavMovie(movie)

    suspend fun removeFavMovie(movie: Movie) = db.getMoviesDao().removeFavMovie(movie)

    suspend fun removeFavTv(tv: TV) = db.getTvDao().removeFavTV(tv)

    suspend fun isFavoritedMovie(movie: Movie?) =
        db.getMoviesDao().getFavMoviesById(movie?.id) != null

    suspend fun isFavoritedTv(tv: TV?) = db.getTvDao().getFavTvById(tv?.id) != null
}