package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository

class TvViewModel(private val repository: RemoteRepository) : ViewModel() {

    fun getTv() = repository.getTV()

}