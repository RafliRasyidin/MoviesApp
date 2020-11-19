package com.rasyidin.moviesapp.utils

import android.util.Log
import com.google.gson.Gson
import com.rasyidin.moviesapp.data.remote.movies.MoviesResponse
import com.rasyidin.moviesapp.data.remote.movies.detail.DetailMovieResponse
import com.rasyidin.moviesapp.data.remote.tv.TVResponse
import com.rasyidin.moviesapp.data.remote.tv.detail.DetailTVResponse
import java.io.IOException
import java.io.InputStream

object DataDummy {

    private val TAG = "DataDummy"
    private var gson = Gson()

    fun generateDummyMovies() =
        gson.fromJson(parsingFileToString("movies.json"), MoviesResponse::class.java).results

    fun generateDummyTv() =
        gson.fromJson(parsingFileToString("tv.json"), TVResponse::class.java).results

    fun generateDummyDetailMovie(): DetailMovieResponse =
        gson.fromJson(parsingFileToString("detailMovie.json"), DetailMovieResponse::class.java)

    fun generateDummyDetailTv(): DetailTVResponse =
        gson.fromJson(parsingFileToString("detailTv.json"), DetailTVResponse::class.java)

    private fun parsingFileToString(fileName: String): String? {
        var json: String? = null
        try {
            val input: InputStream = this.javaClass.classLoader!!.getResourceAsStream(fileName)
            val size = input.available()
            val buffer = ByteArray(size)

            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage.orEmpty())
        }

        return json
    }
}