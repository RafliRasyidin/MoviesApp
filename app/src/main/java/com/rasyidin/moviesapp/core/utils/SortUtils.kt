package com.rasyidin.moviesapp.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val TITLE = "Title"
    const val RATING = "Rating"
    const val RANDOM = "Random"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM favMovies ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
            RATING -> simpleQuery.append("ORDER By voteAverage DESC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTv(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM favTV ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
            RATING -> simpleQuery.append("ORDER By voteAverage DESC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}