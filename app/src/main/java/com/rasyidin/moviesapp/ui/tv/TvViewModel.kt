package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class TvViewModel(private val tvUseCase: TVUseCase) : ViewModel() {

    private var debounceDuration: Long = 300

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    fun getPopularTv() = tvUseCase.getPopularTv().asLiveData()

    val searchTv = queryChannel.asFlow()
        .debounce(debounceDuration)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            tvUseCase.searchTv(it)
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun shouldDebounce(state: Boolean) {
        debounceDuration = if (state) 3000 else 300
    }
}