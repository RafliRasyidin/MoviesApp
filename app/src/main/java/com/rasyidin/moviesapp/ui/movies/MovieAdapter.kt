package com.rasyidin.moviesapp.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.remote.movies.Movie
import com.rasyidin.moviesapp.ui.detail.DetailActivity
import com.rasyidin.moviesapp.ui.detail.DetailActivity.Companion.TYPE_MOVIE
import com.rasyidin.moviesapp.utils.ConstantValue
import kotlinx.android.synthetic.main.item_film.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.FilmViewHolder>() {

    private var listMovies = ArrayList<Movie>()

    fun setMovies(movie: List<Movie>?) {
        if (movie.isNullOrEmpty()) return
        listMovies.clear()
        listMovies.addAll(movie)
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

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                        putExtra(DetailActivity.EXTRA_DETAIL, movie.id)
                        putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}