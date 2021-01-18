package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase

class TvViewModel(private val tvUseCase: TVUseCase) : ViewModel() {

    fun getPopularTv() = tvUseCase.getPopularTv().asLiveData()

    suspend fun searchTv(querySearch: String?) = tvUseCase.searchTv(querySearch)

}