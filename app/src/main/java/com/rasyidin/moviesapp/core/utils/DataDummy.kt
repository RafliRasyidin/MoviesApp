package com.rasyidin.moviesapp.core.utils

object DataDummy {

    /*private val TAG = "DataDummy"
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
    }*/
}