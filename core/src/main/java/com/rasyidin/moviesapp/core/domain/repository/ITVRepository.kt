package com.rasyidin.moviesapp.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.TV
import kotlinx.coroutines.flow.Flow

interface ITVRepository {

    fun getPopularTv(): Flow<Resource<List<TV>>>

    suspend fun searchTv(querySearch: String?): Flow<List<TV>>

    fun getFavTv(): LiveData<PagedList<TV>>

    fun setFavTv(tv: TV, state: Boolean)
}