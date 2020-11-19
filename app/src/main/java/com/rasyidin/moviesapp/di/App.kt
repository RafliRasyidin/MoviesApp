package com.rasyidin.moviesapp.di

import android.app.Application
import com.rasyidin.moviesapp.data.remote.repository.RemoteRepository
import com.rasyidin.moviesapp.ui.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { RemoteRepository() }
        bind() from provider { ViewModelFactory(instance()) }
    }

}