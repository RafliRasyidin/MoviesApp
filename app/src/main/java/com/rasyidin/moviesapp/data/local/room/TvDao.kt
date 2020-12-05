package com.rasyidin.moviesapp.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.rasyidin.moviesapp.data.local.entity.TV

@Dao
interface TvDao {

    @Query("SELECT * FROM favTV")
    fun getFavTV(): DataSource.Factory<Int, TV>

    @Query("SELECT * FROM favTV WHERE id = :tvId")
    suspend fun getFavTvById(tvId: Int?): TV?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavTV(tv: TV)

    @Delete
    suspend fun removeFavTV(tv: TV)
}