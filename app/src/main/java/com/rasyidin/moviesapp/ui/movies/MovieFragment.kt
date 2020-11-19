package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.remote.movies.Movie
import com.rasyidin.moviesapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MovieFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    override val kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private var movies: MutableList<Movie> = mutableListOf()

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
            movieAdapter = MovieAdapter()
            viewModel = ViewModelProvider(
                this,
                viewModelFactory
            ).get(MovieViewModel::class.java)

            observeMovies()

            with(rv_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

    }

    private fun observeMovies() {
        showProgressBar()
        viewModel.getMovies().observe(viewLifecycleOwner, {
            movies.addAll(it)
            movieAdapter.setMovies(movies)
            movieAdapter.notifyDataSetChanged()
            hideProgressBar()
        })
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

}