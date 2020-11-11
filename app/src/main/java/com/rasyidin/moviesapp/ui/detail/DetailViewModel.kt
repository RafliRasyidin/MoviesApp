package com.rasyidin.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.model.Movies
import com.rasyidin.moviesapp.model.Tv
import com.rasyidin.moviesapp.utils.DataDummy

class DetailViewModel: ViewModel() {

    fun getDetailMovie(movieId: Int, listMovies: ArrayList<Movies>): Movies =
            DataDummy.getDetailMovie(movieId, listMovies)

    fun getDetailTv(tvId: Int, listTv: ArrayList<Tv>): Tv =
            DataDummy.getDetailTv(tvId, listTv)

    fun getMovies(): List<Movies> = DataDummy.generateDummyMovies()

    fun getTv(): List<Tv> = DataDummy.generateDummyTv()
}