package com.rasyidin.moviesapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.core.utils.ConstantValue
import com.rasyidin.moviesapp.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailMovieFragmentArgs by navArgs()
    private var movieId: Int? = null

    companion object {
        const val MOVIE_TYPE = "detailMovie"
        const val MOVIE_KEY = 2
        const val FAVORITE_MOVIE_KEY = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Movie"

        val type = arguments?.getInt(MOVIE_TYPE, 2)

        if (type == FAVORITE_MOVIE_KEY) {
            showDetailMovieFromDb()
        } else if (type == MOVIE_KEY) {
            showDetailMovie()
        }

    }

    private fun stateFavorite(state: Boolean) {
        if (state) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    private fun showDetailMovie() {
        movieId = args.detailMovie.id ?: 0
        viewModel.getDetailMovie(movieId)
        viewModel.detailMovie.observe(viewLifecycleOwner) { movie ->
            initView(movie)
            setFavorite(movie)
        }
    }

    private fun showDetailMovieFromDb() {
        movieId = args.detailMovie.id ?: 0
        viewModel.getDetailMovieByIdFromDb(movieId)
        viewModel.detailMovie.observe(viewLifecycleOwner) { movie ->
            initView(movie)
            setFavorite(movie)
        }
    }

    private fun setFavorite(movie: Movie) {
        var statusFavorite = movie.isFavorite
        binding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavMovie(movie, statusFavorite)
            stateFavorite(statusFavorite)
        }
    }

    private fun initView(movie: Movie) {
        binding.apply {
            progressBar.visibility = View.GONE
            tvTitleDetail.text = movie.title
            tvVoteCount.text = movie.voteCount.toString()
            tvOverview.text = movie.overview
            tvPopularity.text = movie.popularity.toString()
            tvReleaseDetail.text = movie.releaseDate
            tvScore.text = movie.voteAverage.toString()
            tvGenres.text = movie.genres[0].name
        }

        Glide.with(this)
            .load(ConstantValue.BASE_URL_IMAGE + movie.posterPath)
            .apply {
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image_black)
            }
            .into(binding.imgDetail)
        Glide.with(this)
            .load(ConstantValue.BASE_URL_IMAGE + movie.backdropPath)
            .apply {
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image_black)
            }
            .into(binding.imgBackdrop)
    }

}
