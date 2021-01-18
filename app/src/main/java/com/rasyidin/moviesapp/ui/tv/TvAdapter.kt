package com.rasyidin.moviesapp.ui.tv

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.utils.ConstantValue
import com.rasyidin.moviesapp.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.ui.base.BaseAdapter

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

    /*private val listTv = ArrayList<TVEntity>()

    fun setListTv(tvEntity: List<TVEntity>?) {
        if (tvEntity.isNullOrEmpty()) return
        listTv.clear()
        listTv.addAll(tvEntity)
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

    var onItemClickListener: ((TVEntity) -> Unit)? = null

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFilmBinding.bind(itemView)
        fun bind(tvEntity: TVEntity?) {
            with(binding) {
                tvTitle.text = tvEntity?.name
                tvRelease.text = tvEntity?.firstAirDate
                tvRate.text = tvEntity?.voteAverage.toString()
                Glide.with(itemView.context)
                    .load(ConstantValue.BASE_URL_IMAGE + tvEntity?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(binding.imgMovie)

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(listTv[adapterPosition])
            }
        }
    }*/
}