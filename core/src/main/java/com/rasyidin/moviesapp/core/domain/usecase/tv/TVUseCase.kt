package com.rasyidin.moviesapp.core.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.TV
import kotlinx.coroutines.flow.Flow

interface TVUseCase {

    fun getPopularTv(): Flow<Resource<List<TV>>>

    suspend fun searchTv(querySearch: String?): Resource<List<TV>>

    fun getFavTv(): LiveData<PagedList<TV>>

    fun setFavTv(tv: TV, state: Boolean)
}