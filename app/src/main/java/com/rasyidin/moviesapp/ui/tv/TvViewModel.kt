package com.rasyidin.moviesapp.ui.tv

import androidx.lifecycle.ViewModel
import com.rasyidin.moviesapp.model.Tv
import com.rasyidin.moviesapp.utils.DataDummy.generateDummyTv

class TvViewModel: ViewModel() {

    fun getTv(): List<Tv> = generateDummyTv()

}