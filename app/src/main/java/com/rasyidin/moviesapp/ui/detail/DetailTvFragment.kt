package com.rasyidin.moviesapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailTvFragmentArgs by navArgs()

    companion object {
        const val DETAIL_TV = "detailTv"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "TV Detail"

        val tv = args.detailTv
        showDetailTv(tv)
    }

    private fun showDetailTv(tv: TV) {
        binding.apply {
            progressBar.visibility = View.GONE
            tvTitleDetail.text = tv.name
            tvVoteCount.text = tv.voteCount.toString()
            tvOverview.text = tv.overview
            tvPopularity.text = tv.popularity.toString()
            tvReleaseDetail.text = tv.firstAirDate
            tvScore.text = tv.voteAverage.toString()

            var statusFavorite = tv.isFavorite
            state(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavTv(tv, statusFavorite)
                state(statusFavorite)
            }
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
            .apply {
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image_black)
            }
            .into(binding.imgDetail)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${tv.backdropPath}")
            .apply {
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image_black)
            }
            .into(binding.imgBackdrop)
    }

    private fun state(state: Boolean) {
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
}