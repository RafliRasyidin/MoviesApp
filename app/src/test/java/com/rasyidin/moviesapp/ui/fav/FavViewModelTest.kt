package com.rasyidin.moviesapp.ui.fav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
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
class FavViewModelTest {

    private lateinit var viewModel: FavViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val repository = Mockito.mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observerMovie: Observer<PagedList<Movie>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TV>>

    @Mock
    private lateinit var pagedListMovie: PagedList<Movie>

    @Mock
    private lateinit var pagedListTv: PagedList<TV>

    @Before
    fun setUp() {
        viewModel = FavViewModel(repository)
    }

    @Test
    fun testGetFavMovies() {
        val dummyFavMovies = pagedListMovie
        `when`(dummyFavMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<Movie>>()
        movies.value = dummyFavMovies

        `when`(repository.getFavMovies()).thenReturn(movies)
        val listFavMovies = viewModel.getFavMovies().value
        verify(repository).getFavMovies()
        assertNotNull(listFavMovies)
        assertEquals(5, listFavMovies?.size)

        viewModel.getFavMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyFavMovies)
    }

    @Test
    fun testGetFavTv() {
        val dummyFavTv = pagedListTv
        `when`(dummyFavTv.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TV>>()
        tv.value = dummyFavTv

        `when`(repository.getFavTv()).thenReturn(tv)
        val listFavTv = viewModel.getFavTv().value
        verify(repository).getFavTv()
        assertNotNull(listFavTv)
        assertEquals(5, listFavTv?.size)

        viewModel.getFavTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyFavTv)
    }

}