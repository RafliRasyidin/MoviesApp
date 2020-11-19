package com.rasyidin.moviesapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.ui.ViewModelFactory
import com.rasyidin.moviesapp.utils.ConstantValue
import kotlinx.android.synthetic.main.activity_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class DetailActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var viewModel: DetailViewModel

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

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(DetailViewModel::class.java)

        if (type == TYPE_MOVIE) {
            supportActionBar?.title = "Detail Movie"
            showMovieDetail(id)

        } else if (type == TYPE_TV) {
            supportActionBar?.title = "Detail Tv"
            showTvDetail(id)
        }

    }

    private fun showMovieDetail(movieId: Int) {
        showProgressBar()
        viewModel.getDetailMovie(movieId).observe(this, {
            tv_title_detail.text = it.title
            tv_vote_count.text = it.voteCount.toString()
            tv_overview.text = it.overview
            tv_popularity.text = it.popularity.toString()
            tv_release_detail.text = it.releaseDate
            tv_score.text = it.voteAverage.toString()
            tv_genres.text = it.genres[0].name
            Glide.with(this)
                .load(ConstantValue.BASE_URL_IMAGE + it.posterPath)
                .apply {
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                }
                .into(img_detail)
            Glide.with(this)
                .load(ConstantValue.BASE_URL_IMAGE + it.backdropPath)
                .apply {
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                }
                .into(img_backdrop)
        })
        hideProgressBar()
    }

    private fun showTvDetail(tvId: Int) {
        showProgressBar()
        viewModel.getDetailTv(tvId).observe(this, {
            tv_title_detail.text = it.name
            tv_vote_count.text = it.voteCount.toString()
            tv_overview.text = it.overview
            tv_popularity.text = it.popularity.toString()
            tv_release_detail.text = it.firstAirDate
            tv_score.text = it.voteAverage.toString()
            tv_genres.text = it.genres[0].name
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                .apply {
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                }
                .into(img_detail)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .apply {
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                }
                .into(img_backdrop)
        })
        hideProgressBar()
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}