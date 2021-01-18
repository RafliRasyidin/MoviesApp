package com.rasyidin.moviesapp.core.di

import androidx.room.Room
import com.rasyidin.moviesapp.core.data.MovieRepository
import com.rasyidin.moviesapp.core.data.TVRepository
import com.rasyidin.moviesapp.core.data.source.local.MovieLocalDataSource
import com.rasyidin.moviesapp.core.data.source.local.TVLocalDataSource
import com.rasyidin.moviesapp.core.data.source.local.room.MoviesDatabase
import com.rasyidin.moviesapp.core.data.source.remote.network.ApiService
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.source.MoviesDataSource
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.source.TvDataSource
import com.rasyidin.moviesapp.core.domain.repository.IMovieRepository
import com.rasyidin.moviesapp.core.domain.repository.ITVRepository
import com.rasyidin.moviesapp.core.utils.AppExecutors
import com.rasyidin.moviesapp.core.utils.ConstantValue.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().getMoviesDao() }
    factory { get<MoviesDatabase>().getTvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "Movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { MovieLocalDataSource(get()) }
    single { TVLocalDataSource(get()) }
    single { MoviesDataSource(get()) }
    single { TvDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
    single<ITVRepository> { TVRepository(get(), get(), get()) }
}