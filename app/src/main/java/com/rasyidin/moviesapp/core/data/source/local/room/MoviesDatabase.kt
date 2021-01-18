package com.rasyidin.moviesapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context): MoviesDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "Movies.db"
            ).build()

    }
}