package com.rasyidin.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository

class DetailViewModel(private val repository: RemoteRepository) : ViewModel() {

    fun getDetailMovie(movieId: Int?) = repository.getDetailMovie(movieId)

    fun getDetailTv(tvId: Int?) = repository.getDetailTV(tvId)

}