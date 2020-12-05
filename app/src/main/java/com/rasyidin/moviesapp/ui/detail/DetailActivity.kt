package com.rasyidin.moviesapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.data.vo.StatusResponse
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
    private var movie: Movie? = null
    private var tv: TV? = null
    private var type: String? = null
    private var menu: Menu? = null

    companion object {
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_MOVIE = "extra movie"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoviesApp)
        setContentView(R.layout.activity_detail)

        tv = intent?.getParcelableExtra(EXTRA_TV)
        movie = intent.getParcelableExtra(EXTRA_MOVIE)
        type = intent.getStringExtra(EXTRA_TYPE)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(DetailViewModel::class.java)

        if (type == TYPE_MOVIE) {
            supportActionBar?.title = "Detail Movie"
            showMovieDetail(movie)

        } else if (type == TYPE_TV) {
            supportActionBar?.title = "Detail Tv"
            showTvDetail(tv)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (type == TYPE_MOVIE) {
            favoriteState(viewModel.isFavoritedMovie(movie))

        } else if (type == TYPE_TV) {
            favoriteState(viewModel.isFavoritedTv(tv))
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_set_fav) {
            if (type == TYPE_MOVIE) {
                if (viewModel.isFavoritedMovie(movie)) {
                    movie?.let { viewModel.removeFavMovie(it) }
                    Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                    favoriteState(false)
                } else {
                    movie?.let { viewModel.setFavMovie(it) }
                    Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
                    favoriteState(true)
                }

            } else if (type == TYPE_TV) {
                if (viewModel.isFavoritedTv(tv)) {
                    tv?.let { viewModel.removeFavTv(it) }
                    Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                    favoriteState(false)
                } else {
                    tv?.let { viewModel.setFavTv(it) }
                    Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
                    favoriteState(true)
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_set_fav)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun showMovieDetail(movie: Movie?) {
        viewModel.getDetailMovie(movie?.id).observe(this, {
            when (it.status) {
                StatusResponse.LOADING -> showProgressBar()
                StatusResponse.SUCCESS -> {
                    hideProgressBar()
                    tv_title_detail.text = it.body?.title
                    tv_vote_count.text = it.body?.voteCount.toString()
                    tv_overview.text = it.body?.overview
                    tv_popularity.text = it.body?.popularity.toString()
                    tv_release_detail.text = it.body?.releaseDate
                    tv_score.text = it.body?.voteAverage.toString()
                    tv_genres.text = it.body?.genres?.get(0)?.name
                    Glide.with(this)
                        .load(ConstantValue.BASE_URL_IMAGE + it.body?.posterPath)
                        .apply {
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_broken_image_black)
                        }
                        .into(img_detail)
                    Glide.with(this)
                        .load(ConstantValue.BASE_URL_IMAGE + it.body?.backdropPath)
                        .apply {
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_broken_image_black)
                        }
                        .into(img_backdrop)
                }
                StatusResponse.ERROR -> {
                    hideProgressBar()
                    Toast.makeText(this, "Something mistakes", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun showTvDetail(tv: TV?) {
        viewModel.getDetailTv(tv?.id).observe(this, {
            when (it.status) {
                StatusResponse.LOADING -> showProgressBar()
                StatusResponse.SUCCESS -> {
                    hideProgressBar()
                    tv_title_detail.text = it.body?.name
                    tv_vote_count.text = it.body?.voteCount.toString()
                    tv_overview.text = it.body?.overview
                    tv_popularity.text = it.body?.popularity.toString()
                    tv_release_detail.text = it.body?.firstAirDate
                    tv_score.text = it.body?.voteAverage.toString()
                    tv_genres.text = it.body?.genres?.get(0)?.name
                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.posterPath}")
                        .apply {
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_broken_image_black)
                        }
                        .into(img_detail)
                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500${it.body?.backdropPath}")
                        .apply {
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_broken_image_black)
                        }
                        .into(img_backdrop)
                }
                StatusResponse.ERROR -> {
                    hideProgressBar()
                    Toast.makeText(this, "Something mistakes", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}