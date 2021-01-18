package com.rasyidin.moviesapp.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {

    @Query("SELECT * FROM favTV")
    fun getAllTV(): Flow<List<TVEntity>>

    @Query("SELECT * FROM favTV WHERE isFavorite = 1")
    fun getFavTV(): DataSource.Factory<Int, TVEntity>

    /*@Query("SELECT * FROM favTV WHERE id = :tvId")
    suspend fun getFavTvById(tvId: Int?): Flow<TVEntity>?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTV(tv: List<TVEntity>)

    @Update
    fun updateFavTV(tv: TVEntity)

    @Delete
    fun removeFavTV(tv: TVEntity)
}