package com.rasyidin.moviesapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rasyidin.moviesapp.core.data.source.local.entity.MovieEntity
import com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity

@Database(
    entities = [MovieEntity::class, TVEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getTvDao(): TvDao

}