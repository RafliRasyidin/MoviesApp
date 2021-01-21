package com.rasyidin.moviesapp.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {

    @Query("SELECT * FROM favTV")
    fun getAllTV(): Flow<List<TVEntity>>

    @Query("SELECT * FROM favTV WHERE isFavorite = 1")
    fun getFavTV(): DataSource.Factory<Int, TVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTV(tv: List<TVEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertFavTV(tv: TVEntity)

}