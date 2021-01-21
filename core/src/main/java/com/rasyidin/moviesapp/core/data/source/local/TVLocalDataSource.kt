package com.rasyidin.moviesapp.core.data.source.local

import androidx.paging.DataSource
import com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity
import com.rasyidin.moviesapp.core.data.source.local.room.TvDao
import kotlinx.coroutines.flow.Flow

class TVLocalDataSource(private val tvDao: TvDao) {

    fun getAllTV(): Flow<List<TVEntity>> = tvDao.getAllTV()

    fun getFavTv(): DataSource.Factory<Int, TVEntity> = tvDao.getFavTV()

    suspend fun insertTV(tv: List<TVEntity>) = tvDao.insertTV(tv)

    fun setFavoriteTV(tv: TVEntity, newState: Boolean) {
        tv.isFavorite = newState
        tvDao.upsertFavTV(tv)
    }

}