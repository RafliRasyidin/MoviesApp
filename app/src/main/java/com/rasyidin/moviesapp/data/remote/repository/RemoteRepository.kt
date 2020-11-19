package com.rasyidin.moviesapp.data.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rasyidin.moviesapp.data.remote.api.ApiConfig
import com.rasyidin.moviesapp.data.remote.movies.Movie
import com.rasyidin.moviesapp.data.remote.movies.MoviesResponse
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.TV
import com.rasyidin.moviesapp.data.remote.tv.TVResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository : Repository {

    companion object {
        private const val TAG = "RemoteRepository"
    }

    override fun getMovies(): LiveData<List<Movie>> {
        val movies: MutableLiveData<List<Movie>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.let {
                    movies.postValue(it.results)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return movies
    }

    override fun getTV(): LiveData<List<TV>> {
        val tv: MutableLiveData<List<TV>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTV().enqueue(object : Callback<TVResponse> {
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                response.body()?.let {
                    tv.postValue(it.results)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return tv
    }

    override fun getDetailMovie(id: Int?): LiveData<DetailMovieResponse> {
        val movieItem: MutableLiveData<DetailMovieResponse> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getMovieDetail(id)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    response.body()?.let {
                        movieItem.postValue(it)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
        return movieItem
    }

    override fun getDetailTV(id: Int?): LiveData<DetailTVResponse> {
        val tvItem: MutableLiveData<DetailTVResponse> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTVDetail(id).enqueue(object : Callback<DetailTVResponse> {
            override fun onResponse(
                call: Call<DetailTVResponse>,
                response: Response<DetailTVResponse>
            ) {
                response.body()?.let {
                    tvItem.postValue(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailTVResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return tvItem
    }
}