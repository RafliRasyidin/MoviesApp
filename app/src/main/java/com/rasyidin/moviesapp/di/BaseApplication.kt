package com.rasyidin.moviesapp.di

import android.app.Application
import com.rasyidin.moviesapp.data.local.LocalDataSource
import com.rasyidin.moviesapp.data.local.room.MoviesDatabase
import com.rasyidin.moviesapp.data.remote.RemoteDataSource
import com.rasyidin.moviesapp.data.repository.MovieCatalogueRepository
import com.rasyidin.moviesapp.ui.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BaseApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        bind() from singleton { MoviesDatabase(instance()) }
        bind() from singleton { LocalDataSource(instance()) }
        bind() from singleton { RemoteDataSource() }
        bind() from singleton { MovieCatalogueRepository(instance(), instance()) }
        bind() from provider { ViewModelFactory(instance()) }
    }

}