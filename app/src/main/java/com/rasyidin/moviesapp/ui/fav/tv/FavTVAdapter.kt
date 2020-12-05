package com.rasyidin.moviesapp.ui.fav.tv

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
import com.rasyidin.moviesapp.data.local.entity.TV
import com.rasyidin.moviesapp.ui.detail.DetailActivity
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.EXTRA_TV
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.TYPE_TV
import com.rasyidin.moviesapp.utils.ConstantValue
import kotlinx.android.synthetic.main.item_film.view.*

class FavTVAdapter internal constructor() :
    PagedListAdapter<TV, FavTVAdapter.TVViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return TVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class TVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: TV) {
            with(itemView) {
                tv_title.text = tv.name
                tv_release.text = tv.firstAirDate
                tv_rate.text = tv.voteAverage.toString()
                Glide.with(context)
                    .load(ConstantValue.BASE_URL_IMAGE + tv.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(img_movie)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_TV, tv)
                        putExtra(EXTRA_TYPE, TYPE_TV)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    fun getSwipeData(swipedPosition: Int): TV? = getItem(swipedPosition)
}