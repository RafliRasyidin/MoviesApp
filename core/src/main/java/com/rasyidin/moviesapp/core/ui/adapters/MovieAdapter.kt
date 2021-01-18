package com.rasyidin.moviesapp.core.ui.adapters

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.core.R
import com.rasyidin.moviesapp.core.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.base.BaseAdapter
import com.rasyidin.moviesapp.core.utils.ConstantValue

class MovieAdapter :
    BaseAdapter<Movie>(R.layout.item_film) {

    override val data: ArrayList<Movie> = ArrayList()

    override fun onBindViewHolder(holderBase: BaseViewHolder, position: Int) {
        val list = data[position]
        val binding = ItemFilmBinding.bind(holderBase.itemView)
        binding.apply {
            tvTitle.text = list.title
            tvRate.text = list.voteAverage.toString()
            tvRelease.text = list.releaseDate

            Glide.with(root.context)
                .load(ConstantValue.BASE_URL_IMAGE + list.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                )
                .into(imgMovie)

            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(list)
                }
            }
        }
    }

}