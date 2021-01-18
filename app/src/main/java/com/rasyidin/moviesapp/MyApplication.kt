package com.rasyidin.moviesapp

import android.app.Application
import com.rasyidin.moviesapp.core.di.databaseModule
import com.rasyidin.moviesapp.core.di.networkModule
import com.rasyidin.moviesapp.core.di.repositoryModule
import com.rasyidin.moviesapp.di.useCaseModule
import com.rasyidin.moviesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}