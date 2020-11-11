package com.rasyidin.moviesapp.model

data class Movies(
        val movieId: Int,
        var title: String,
        var releaseDate: String,
        var score: String,
        var overview: String,
        var image: Int,
        var genre: String
)