package com.rasyidin.moviesapp.core.data.source.remote.response.tv.source

import android.util.Log
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiService
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.TVResponse
import com.rasyidin.moviesapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TvDataSource(private val apiService: ApiService) {

    companion object {
        const val TAG = "TvDataSource"
    }

    fun getPopularTv(): Flow<ApiResponse<List<TVResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getPopularTV()
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    EspressoIdlingResource.decrement()
                } else {
                    emit(ApiResponse.Empty)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTv(tvId: Int?): Flow<TVResponse> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getTVDetail(tvId)
                emit(response)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTv(querySearch: String?): Flow<List<TVResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.searchTv(querySearch)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(response.results)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

}