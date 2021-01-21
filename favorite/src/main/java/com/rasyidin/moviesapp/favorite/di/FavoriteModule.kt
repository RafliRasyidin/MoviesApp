package com.rasyidin.moviesapp.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavViewModel(get(), get()) }
}