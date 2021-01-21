package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.*
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class TvViewModel(private val tvUseCase: TVUseCase) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _search = MutableLiveData<List<TV>>()
    val search: LiveData<List<TV>> = _search

    private fun setLoading() = _loading.postValue(true)

    private fun finishLoading() = _loading.postValue(false)

    fun getPopularTv() = tvUseCase.getPopularTv().asLiveData()

    fun searchTv(querySearch: String?) {
        viewModelScope.launch {
            tvUseCase.searchTv(querySearch).onStart {
                setLoading()
            }.collect {
                _search.postValue(it)
                finishLoading()
            }
        }
    }

}