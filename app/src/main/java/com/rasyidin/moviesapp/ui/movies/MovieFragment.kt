package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.moviesapp.R
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MovieViewModel::class.java)

            val movies = viewModel.getMovies()

            if (movies.isEmpty()) {
                dataIsEmpty()
            }

            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            with(rv_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }

    private fun dataIsEmpty() {
        rv_movies.visibility = View.GONE
        img_no_data.visibility = View.VISIBLE
    }

}