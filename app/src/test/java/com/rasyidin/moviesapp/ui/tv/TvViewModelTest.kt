package com.rasyidin.moviesapp.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import com.rasyidin.moviesapp.data.vo.Resource
import com.rasyidin.moviesapp.utils.DataDummy
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
    private val repository = Mockito.mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<Resource<List<TV>>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(repository)
    }

    @Test
    fun testGetTv() {
        val dummyTv = Resource.success(DataDummy.generateDummyTv())
        val tv = MutableLiveData<Resource<List<TV>>>()

        tv.value = dummyTv
        `when`(repository.getTV()).thenReturn(tv)
        val listMovies = viewModel.getTv().value
        verify<MovieCatalogueRepository>(repository).getTV()
        assertNotNull(listMovies)
        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)

    }
}