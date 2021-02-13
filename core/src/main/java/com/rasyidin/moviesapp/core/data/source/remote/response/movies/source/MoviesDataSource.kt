package com.rasyidin.moviesapp.core.data.source.remote.response.movies.source

import android.util.Log
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiService
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.MoviesResponse
import com.rasyidin.moviesapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MoviesDataSource(private val apiService: ApiService) {

    companion object {
        const val TAG = "MoviesDataSource"
    }

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getPopularMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
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

    suspend fun searchMovies(querySearch: String?): Flow<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.searchMovies(querySearch)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    EspressoIdlingResource.decrement()
                } else {
                    emit(ApiResponse.Empty)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                emit(ApiResponse.Error(e.message.toString()))
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

}