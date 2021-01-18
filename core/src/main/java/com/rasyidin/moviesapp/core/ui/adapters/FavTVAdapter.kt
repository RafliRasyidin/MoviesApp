package com.rasyidin.moviesapp.core.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.core.R
import com.rasyidin.moviesapp.core.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.base.BasePagedListAdapter
import com.rasyidin.moviesapp.core.utils.ConstantValue

class FavTVAdapter(onClick: (TV) -> Unit) :
    BasePagedListAdapter<TV>(diffUtil = DIFF_CALLBACK, onClick = onClick) {

    override val getLayoutIdRes: Int
        get() = R.layout.item_film

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TV>() {
            override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = getItem(position)
        val binding = ItemFilmBinding.bind(holder.itemView)
        binding.apply {
            tvTitle.text = tv?.name
            tvRelease.text = tv?.firstAirDate
            tvRate.text = tv?.voteAverage.toString()
            Glide.with(root.context)
                .load(ConstantValue.BASE_URL_IMAGE + tv?.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_image_black)
                )
                .into(imgMovie)

            root.setOnClickListener { onItemClick(tv) }
        }
    }

}