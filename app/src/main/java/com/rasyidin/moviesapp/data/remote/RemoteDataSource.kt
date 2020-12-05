package com.rasyidin.moviesapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.remote.api.ApiConfig
import com.rasyidin.moviesapp.data.remote.movies.MoviesResponse
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.TVResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.data.vo.Resource
import com.rasyidin.moviesapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteRepository"
    }

    private val movies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()
    private val tv: MutableLiveData<Resource<List<TV>>> = MutableLiveData()
    private val tvItem: MutableLiveData<Resource<DetailTVResponse>> = MutableLiveData()
    private val movieItem: MutableLiveData<Resource<DetailMovieResponse>> = MutableLiveData()

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        EspressoIdlingResource.increment()
        movies.postValue(Resource.loading(null))
        ApiConfig.getApiService().getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {

                movies.postValue(handleMoviesResponse(response))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                movies.postValue(Resource.error(t.message, null))
            }
        })
        return movies
    }

    private fun handleMoviesResponse(response: Response<MoviesResponse>): Resource<List<Movie>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse.results)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getTv(): LiveData<Resource<List<TV>>> {
        EspressoIdlingResource.increment()
        tv.postValue(Resource.loading(null))
        ApiConfig.getApiService().getTV().enqueue(object : Callback<TVResponse> {
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                tv.postValue(handleTvResponse(response))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                tv.postValue(Resource.error(t.message, null))
            }
        })
        return tv
    }

    private fun handleTvResponse(response: Response<TVResponse>): Resource<List<TV>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse.results)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getDetailTv(tvId: Int?): LiveData<Resource<DetailTVResponse>> {
        EspressoIdlingResource.increment()
        tvItem.postValue(Resource.loading(null))
        ApiConfig.getApiService().getTVDetail(tvId).enqueue(object : Callback<DetailTVResponse> {
            override fun onResponse(
                call: Call<DetailTVResponse>,
                response: Response<DetailTVResponse>
            ) {
                tvItem.postValue(handleDetailTvResponse(response))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTVResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                tvItem.postValue(Resource.error(t.message, null))
            }
        })
        return tvItem
    }

    private fun handleDetailTvResponse(response: Response<DetailTVResponse>): Resource<DetailTVResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse)
            }
        }
        return Resource.error(response.message(), null)
    }

    fun getDetailMovie(movieId: Int?): LiveData<Resource<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        movieItem.postValue(Resource.loading(null))
        ApiConfig.getApiService().getMovieDetail(movieId)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    movieItem.postValue(handleDetailMovieResponse(response))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
        return movieItem
    }

    private fun handleDetailMovieResponse(response: Response<DetailMovieResponse>): Resource<DetailMovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.success(resultResponse)
            }
        }
        return Resource.error(response.message(), null)
    }
}