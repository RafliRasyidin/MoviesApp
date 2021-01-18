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

    fun mapTvResponseToEntities(input: List<TVResponse>): List<com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity> {
        val tvList = ArrayList<com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity>()
        input.map {
            val tv = com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity(
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

    fun mapMovieResponseToMovie(input: MoviesResponse): Movie =
        Movie(
            backdropPath = input.backdropPath,
            genres = input.genres,
            id = input.id,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            title = input.title,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount
        )

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

    fun mapTvResponseToTv(input: TVResponse): TV =
        TV(
            backdropPath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            genres = input.genres,
            id = input.id,
            name = input.name,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount
        )

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
                isFavorite = false
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
            isFavorite = false
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
                isFavorite = false
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
            isFavorite = false
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
            isFavorite = false
        )

    fun mapTvToTvEntity(input: TV) =
        com.rasyidin.moviesapp.core.data.source.local.entity.TVEntity(
            id = input.id,
            firstAirDate = input.firstAirDate,
            name = input.name,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            popularity = input.popularity,
            isFavorite = false
        )
}