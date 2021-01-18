package com.rasyidin.moviesapp.ui.fav.movies

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.utils.ConstantValue
import com.rasyidin.moviesapp.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.ui.base.BasePagedListAdapter

class FavMovieAdapter(onClick: (Movie) -> Unit) :
    BasePagedListAdapter<Movie>(diffUtil = DIFF_CALLBACK, onClick = onClick) {

    override val getLayoutIdRes: Int
        get() = R.layout.item_film

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        val binding = ItemFilmBinding.bind(holder.itemView)
        binding.apply {
            tvTitle.text = movie?.title
            tvRate.text = movie?.voteAverage.toString()
            tvRelease.text = movie?.releaseDate
            Glide.with(root.context)
                .load(ConstantValue.BASE_URL_IMAGE + movie?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                )
                .into(imgMovie)

            root.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

}