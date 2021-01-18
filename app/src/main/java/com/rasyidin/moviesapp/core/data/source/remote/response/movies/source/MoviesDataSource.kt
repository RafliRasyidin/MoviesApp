package com.rasyidin.moviesapp.core.data.source.remote.response.movies.source

import android.util.Log
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiConfig
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.MoviesResponse
import com.rasyidin.moviesapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesDataSource {

    companion object {
        const val TAG = "MoviesDataSource"
    }

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().getPopularMovies()
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

    suspend fun searchMovies(querySearch: String?): Flow<List<MoviesResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().searchMovies(querySearch)
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

    suspend fun getDetailMovie(movieId: Int?): Flow<MoviesResponse> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = ApiConfig.getApiService().getMovieDetail(movieId)
                emit(response)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

}