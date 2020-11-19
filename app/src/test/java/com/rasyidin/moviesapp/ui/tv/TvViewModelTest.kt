package com.rasyidin.moviesapp.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository
import com.rasyidin.moviesapp.data.remote.tv.TV
import com.rasyidin.moviesapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val repository = Mockito.mock(RemoteRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<TV>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(repository)
    }

    @Test
    fun testGetTv() {
        val dummyTv = DataDummy.generateDummyTv()
        val tv = MutableLiveData<List<TV>>()

        tv.postValue(dummyTv)
        `when`(repository.getTV()).thenReturn(tv)
        val listMovies = viewModel.getTv().value
        verify<RemoteRepository>(repository).getTV()
        assertNotNull(listMovies)
        assertEquals(dummyTv.size, listMovies?.size)
        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)

    }
}