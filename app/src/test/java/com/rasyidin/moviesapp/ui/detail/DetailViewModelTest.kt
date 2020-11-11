package com.rasyidin.moviesapp.ui.detail

import com.rasyidin.moviesapp.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.tvId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetDetailMovie() {
        val movies = viewModel.getMovies()
        val detailMovie = viewModel.getDetailMovie(movieId, movies as ArrayList)
        assertNotNull(detailMovie)
        assertEquals(dummyMovie.title, detailMovie.title)
        assertEquals(dummyMovie.image, detailMovie.image)
        assertEquals(dummyMovie.genre, detailMovie.genre)
        assertEquals(dummyMovie.overview, detailMovie.overview)
        assertEquals(dummyMovie.releaseDate, detailMovie.releaseDate)
        assertEquals(dummyMovie.score, detailMovie.score)
    }

    @Test
    fun testGetDetailTv() {
        val tv = viewModel.getTv()
        val detailTv = viewModel.getDetailTv(tvId, tv as ArrayList)
        assertNotNull(detailTv)
        assertEquals(dummyTv.image, detailTv.image)
        assertEquals(dummyTv.genres, detailTv.genres)
        assertEquals(dummyTv.overview, detailTv.overview)
        assertEquals(dummyTv.release, detailTv.release)
        assertEquals(dummyTv.score, detailTv.score)
        assertEquals(dummyTv.title, detailTv.title)
    }

}