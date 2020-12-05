package com.rasyidin.moviesapp.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.rasyidin.moviesapp.data.local.entity.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favMovies")
    fun getFavMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM favMovies WHERE id = :moviesId")
    suspend fun getFavMoviesById(moviesId: Int?): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavMovie(movie: Movie)

    @Delete
    suspend fun removeFavMovie(movie: Movie)

}