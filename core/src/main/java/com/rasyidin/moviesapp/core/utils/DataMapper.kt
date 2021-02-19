package com.rasyidin.moviesapp.core.utils

import com.rasyidin.moviesapp.core.data.source.local.entity.MovieEntity
import com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity
import com.rasyidin.moviesapp.core.data.source.remote.response.movies.MoviesResponse
import com.rasyidin.moviesapp.core.data.source.remote.response.tv.TVResponse
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.domain.model.TV

object DataMapper {

    fun mapMoviesResponseToEntities(input: List<MoviesResponse>): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                title = it.title,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapTvResponseToEntities(input: List<TVResponse>): List<TVEntity> {
        val tvList = ArrayList<TVEntity>()
        input.map {
            val tv = TVEntity(
                id = it.id,
                firstAirDate = it.firstAirDate,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                isFavorite = false
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapMoviesResponseToMovies(input: List<MoviesResponse>): List<Movie> {
        val movies = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                title = it.title,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapTVResponseToListTV(input: List<TVResponse>): List<TV> {
        val listTV = ArrayList<TV>()
        input.map {
            val movie = TV(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                firstAirDate = it.firstAirDate,
                name = it.name,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            listTV.add(movie)
        }
        return listTV
    }

    fun mapMovieEntitiesToMovie(input: List<MovieEntity>) =
        input.map {
            Movie(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                title = it.title,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieEntityToMovie(input: MovieEntity) =
        Movie(
            id = input.id,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            releaseDate = input.releaseDate,
            title = input.title,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

    fun mapTvEntitiesToTv(input: List<TVEntity>) =
        input.map {
            TV(
                id = it.id,
                firstAirDate = it.firstAirDate,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                overview = it.overview,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                isFavorite = it.isFavorite
            )
        }

    fun mapTvEntityToTV(input: TVEntity) =
        TV(
            id = input.id,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            firstAirDate = input.firstAirDate,
            name = input.name,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

    fun mapMovieToMovieEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            releaseDate = input.releaseDate,
            title = input.title,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

    fun mapTvToTvEntity(input: TV) =
        TVEntity(
            id = input.id,
            firstAirDate = input.firstAirDate,
            name = input.name,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            popularity = input.popularity,
            isFavorite = input.isFavorite
        )
}