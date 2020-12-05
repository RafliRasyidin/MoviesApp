package com.rasyidin.moviesapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import com.rasyidin.moviesapp.data.vo.Resource
import com.rasyidin.moviesapp.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val repository = Mockito.mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovies = Resource.success(DataDummy.generateDummyMovies())
        val movies = MutableLiveData<Resource<List<Movie>>>()
        movies.postValue(dummyMovies)

        `when`(repository.getMovies()).thenReturn(movies)
        val listMovies = viewModel.getMovies().value
        verify<MovieCatalogueRepository>(repository).getMovies()
        Assert.assertNotNull(listMovies)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}