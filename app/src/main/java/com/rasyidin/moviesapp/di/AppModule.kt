package com.rasyidin.moviesapp.di

import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieInteractor
import com.rasyidin.moviesapp.core.domain.usecase.movie.MovieUseCase
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVInteractor
import com.rasyidin.moviesapp.core.domain.usecase.tv.TVUseCase
import com.rasyidin.moviesapp.ui.detail.DetailViewModel
import com.rasyidin.moviesapp.ui.fav.FavViewModel
import com.rasyidin.moviesapp.ui.movies.MovieViewModel
import com.rasyidin.moviesapp.ui.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TVUseCase> { TVInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavViewModel(get(), get()) }
}