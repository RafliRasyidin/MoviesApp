package com.rasyidin.moviesapp.core.ui.adapters

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.core.R
import com.rasyidin.moviesapp.core.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.base.BaseAdapter
import com.rasyidin.moviesapp.core.utils.ConstantValue

class TvAdapter : BaseAdapter<TV>(R.layout.item_film) {

    override val data: ArrayList<TV> = ArrayList()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val list = data[position]
        val binding = ItemFilmBinding.bind(holder.itemView)
        binding.apply {
            tvTitle.text = list.name
            tvRelease.text = list.firstAirDate
            tvRate.text = list.voteAverage.toString()

            Glide.with(holder.itemView.context)
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