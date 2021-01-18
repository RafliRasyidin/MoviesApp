package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.databinding.FragmentMoviesBinding
import com.rasyidin.moviesapp.ui.base.BaseFragment
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment.Companion.MOVIE_KEY
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMoviesBinding>(R.layout.fragment_movies) {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Movies"

        setupRecyclerView()

        subscribeToObservers()

        movieAdapter.setItemClickListener {
            navigateToDetailMovie(it)
        }
    }

    private fun setupRecyclerView() = binding.rvMovies.apply {
        movieAdapter = MovieAdapter()
        layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = movieAdapter
        setHasFixedSize(true)
    }

    private fun subscribeToObservers() {
        viewModel.getPopularMovies().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> showProgressBar()
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { movies ->
                        movieAdapter.setList(movies)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, "Something mistakes", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun navigateToDetailMovie(movie: Movie) {
        val bundle = Bundle().apply {
            putSerializable(DetailMovieFragment.MOVIE_TYPE, movie)
        }
        findNavController().navigate(
            R.id.action_movieFragment_to_detailFragment,
            bundle
        )
        bundle.putInt(DetailMovieFragment.MOVIE_TYPE, MOVIE_KEY)
        val fragment = Fragment()
        fragment.arguments = bundle
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

}