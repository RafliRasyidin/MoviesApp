package com.rasyidin.moviesapp.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.model.Tv
import com.rasyidin.moviesapp.ui.detail.DetailActivity
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.TYPE_TV
import kotlinx.android.synthetic.main.item_film.view.*

class TvAdapter :
    RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private val listTv = ArrayList<Tv>()

    fun setListTv(tv: List<Tv>?) {
        if (tv == null) return
        listTv.clear()
        listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int {
        return listTv.size
    }

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: Tv) {
            with(itemView) {
                tv_title.text = tv.title
                tv_release.text = tv.release
                tv_rate.text = tv.score
                Glide.with(context)
                    .load(tv.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(img_movie)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL, tv.tvId)
                        putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}