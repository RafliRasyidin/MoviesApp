package com.rasyidin.moviesapp.ui.movies

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.utils.ConstantValue
import com.rasyidin.moviesapp.databinding.ItemFilmBinding
import com.rasyidin.moviesapp.ui.base.BaseAdapter

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


    /*private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movieEntity: List<MovieEntity>?) {
        if (movieEntity.isNullOrEmpty()) return
        listMovies.clear()
        listMovies.addAll(movieEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    var onItemClickListener: ((MovieEntity) -> Unit)? = null

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFilmBinding.bind(itemView)
        fun bind(movieEntity: MovieEntity?) {
            with(binding) {
                tvTitle.text = movieEntity?.title
                tvRate.text = movieEntity?.voteAverage.toString()
                tvRelease.text = movieEntity?.releaseDate
                Glide.with(itemView.context)
                    .load(ConstantValue.BASE_URL_IMAGE + movieEntity?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(imgMovie)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(listMovies[adapterPosition])
            }
        }
    }*/
}