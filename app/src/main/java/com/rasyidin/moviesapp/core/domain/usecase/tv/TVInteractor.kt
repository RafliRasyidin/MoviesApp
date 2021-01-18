package com.rasyidin.moviesapp.core.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.domain.repository.ITVRepository
import kotlinx.coroutines.flow.Flow

class TVInteractor(private val tvRepository: ITVRepository) : TVUseCase {

    override fun getPopularTv(): Flow<Resource<List<TV>>> =
        tvRepository.getPopularTv()

    override suspend fun getDetailTV(id: Int?): Flow<TV> =
        tvRepository.getDetailTV(id)

    override suspend fun searchTv(querySearch: String?): Flow<List<TV>> =
        tvRepository.searchTv(querySearch)

    override fun getFavTv(): LiveData<PagedList<TV>> =
        tvRepository.getFavTv()

    override fun setFavTv(tv: TV, state: Boolean) {
        tvRepository.setFavTv(tv, state)
    }

}