package com.rasyidin.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rasyidin.moviesapp.data.local.LocalDataSource
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.remote.RemoteDataSource
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import com.rasyidin.moviesapp.data.vo.Resource

class MovieCatalogueRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieCatalogueDataSource {

    override fun getMovies(): LiveData<Resource<List<Movie>>> = remoteDataSource.getMovies()

    override fun getTV(): LiveData<Resource<List<TV>>> = remoteDataSource.getTv()

    override fun getDetailMovie(id: Int?): LiveData<Resource<DetailMovieResponse>> =
        remoteDataSource.getDetailMovie(id)

    override fun getDetailTV(id: Int?): LiveData<Resource<DetailTVResponse>> =
        remoteDataSource.getDetailTv(id)

    override fun getFavMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override suspend fun removeFavMovie(movie: Movie) = localDataSource.removeFavMovie(movie)

    override suspend fun removeFavTv(tv: TV) = localDataSource.removeFavTv(tv)

    override fun getFavTv(): LiveData<PagedList<TV>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }

    override suspend fun setFavMovie(movie: Movie) {
        localDataSource.setFavMovie(movie)
    }

    override suspend fun setFavTv(tv: TV) {
        localDataSource.setFavTv(tv)
    }

    override suspend fun isFavoritedMovie(movie: Movie?): Boolean {
        return localDataSource.isFavoritedMovie(movie)
    }

    override suspend fun isFavoritedTv(tv: TV?): Boolean {
        return localDataSource.isFavoritedTv(tv)
    }
}