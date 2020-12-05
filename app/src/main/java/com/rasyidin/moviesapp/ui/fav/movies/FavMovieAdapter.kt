package com.rasyidin.moviesapp.ui.fav.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.local.entity.Movie
import com.rasyidin.moviesapp.ui.detail.DetailActivity
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.TYPE_MOVIE
import com.rasyidin.moviesapp.utils.ConstantValue
import kotlinx.android.synthetic.main.item_film.view.*

class FavMovieAdapter internal constructor() :
    PagedListAdapter<Movie, FavMovieAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_title.text = movie.title
                tv_rate.text = movie.voteAverage.toString()
                tv_release.text = movie.releaseDate
                Glide.with(context)
                    .load(ConstantValue.BASE_URL_IMAGE + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(img_movie)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_MOVIE, movie)
                        putExtra(EXTRA_TYPE, TYPE_MOVIE)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    fun getSwipeData(swipedPosition: Int): Movie? = getItem(swipedPosition)
}