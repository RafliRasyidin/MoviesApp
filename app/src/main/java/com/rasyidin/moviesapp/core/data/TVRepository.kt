package com.rasyidin.moviesapp.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rasyidin.moviesapp.core.data.source.local.TVLocalDataSource
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.TVResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.source.TvDataSource
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.domain.repository.ITVRepository
import com.rasyidin.moviesapp.core.utils.AppExecutors
import com.rasyidin.moviesapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TVRepository(
    private val localDataSource: TVLocalDataSource,
    private val remoteDataSource: TvDataSource,
    private val appExecutors: AppExecutors
) : ITVRepository {

    override fun getPopularTv(): Flow<Resource<List<TV>>> =
        object : NetworkBoundResource<List<TV>, List<TVResponse>>() {
            override fun loadFromDb(): Flow<List<TV>> {
                return localDataSource.getAllTV().map {
                    DataMapper.mapTvEntitiesToTv(it)
                }
            }

            override fun shouldFetch(data: List<TV>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TVResponse>>> {
                return remoteDataSource.getPopularTv()
            }

            override suspend fun saveCallResult(data: List<TVResponse>) {
                val listTv = DataMapper.mapTvResponseToEntities(data)
                return localDataSource.insertTV(listTv)
            }
        }.asFlow()

    override suspend fun getDetailTV(id: Int?): Flow<TV> =
        remoteDataSource.getDetailTv(id).map {
            DataMapper.mapTvResponseToTv(it)
        }

    override suspend fun searchTv(querySearch: String?): Flow<List<TV>> =
        remoteDataSource.searchTv(querySearch).map {
            DataMapper.mapTVResponseToListTV(it)
        }

    override fun getFavTv(): LiveData<PagedList<TV>> {
        val data = localDataSource.getFavTv().map {
            DataMapper.mapTvEntityToTV(it)
        }
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(data, config).build()
    }

    override fun setFavTv(tv: TV, state: Boolean) {
        val tvEntity = DataMapper.mapTvToTvEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTV(tvEntity, state) }
    }
}