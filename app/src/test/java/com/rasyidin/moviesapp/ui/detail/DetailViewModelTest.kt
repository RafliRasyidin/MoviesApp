package com.rasyidin.moviesapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: DetailViewModel
    private var dummyMovie = DataDummy.generateDummyDetailMovie()
    private val movieId = dummyMovie.id
    private var dummyTv = DataDummy.generateDummyDetailTv()
    private val tvId = dummyTv.id

    @Mock
    private val repository = mock(RemoteRepository::class.java)

    @Mock
    private lateinit var movieObserver: Observer<DetailMovieResponse>

    @Mock
    private lateinit var tvObserver: Observer<DetailTVResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun testGetDetailMovie() {
        val movie = MutableLiveData<DetailMovieResponse>()

        movie.postValue(dummyMovie)
        `when`(repository.getDetailMovie(movieId)).thenReturn(movie)
        val movieEntities = viewModel.getDetailMovie(movieId).value

        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(dummyMovie.title, movieEntities?.title)
        viewModel.getDetailMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)

    }

    @Test
    fun testGetDetailTv() {
        val tv = MutableLiveData<DetailTVResponse>()

        tv.postValue(dummyTv)
        `when`(repository.getDetailTV(tvId)).thenReturn(tv)
        val tvEntities = viewModel.getDetailTv(tvId).value

        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(dummyTv.name, tvEntities?.name)
        viewModel.getDetailTv(tvId).observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)
    }

}