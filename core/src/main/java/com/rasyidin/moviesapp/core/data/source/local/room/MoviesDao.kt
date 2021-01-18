package com.rasyidin.moviesapp.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.rasyidin.moviesapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favMovies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favMovies WHERE isFavorite = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favMovies WHERE id = :moviesId")
    fun getFavMoviesById(moviesId: Int?): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: List<MovieEntity>)

    @Update
    fun updateFavMovie(movieEntity: MovieEntity)

}