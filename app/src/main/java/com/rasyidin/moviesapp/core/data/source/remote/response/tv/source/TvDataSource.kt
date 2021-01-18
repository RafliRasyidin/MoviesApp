package com.rasyidin.moviesapp.core.data.source.remote.response.tv.source

import android.util.Log
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiConfig
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.TVResponse
import com.rasyidin.moviesapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvDataSource {

    companion object {
        const val TAG = "TvDataSource"
    }

    fun getPopularTv(): Flow<ApiResponse<List<TVResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().getPopularTV()
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTv(tvId: Int?): Flow<TVResponse> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().getTVDetail(tvId)
                emit(response)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTv(querySearch: String?): Flow<List<TVResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().searchTv(querySearch)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(response.results)
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}