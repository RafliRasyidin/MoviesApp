package com.rasyidin.moviesapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.model.Movies
import com.rasyidin.moviesapp.model.Tv
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoviesApp)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(EXTRA_DETAIL, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        if (type == TYPE_MOVIE) {
            val listMovies = viewModel.getMovies()
            val movie = viewModel.getDetailMovie(id, listMovies as ArrayList)
            supportActionBar?.title = "Detail Movie"
            showMovieDetail(movie)

        } else if (type == TYPE_TV) {
            val listTv = viewModel.getTv()
            val tv = viewModel.getDetailTv(id, listTv as ArrayList)
            supportActionBar?.title = "Detail Tv"
            showTvDetail(tv)
        }

    }

    private fun showMovieDetail(movies: Movies) {
        tv_title_detail.text = movies.title
        tv_release_detail.text = movies.releaseDate
        tv_genres.text = movies.genre
        tv_overview.text = movies.overview
        tv_score.text = movies.score
        Glide.with(this)
            .load(movies.image)
            .into(img_detail)

        btn_favorite.setOnClickListener {
            Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showTvDetail(tv: Tv) {
        tv_title_detail.text = tv.title
        tv_release_detail.text = tv.release
        tv_genres.text = tv.genres
        tv_overview.text = tv.overview
        tv_score.text = tv.score
        Glide.with(this)
            .load(tv.image)
            .into(img_detail)

        btn_favorite.setOnClickListener {
            Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
        }
    }
}