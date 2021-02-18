package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.adapters.MovieAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentMoviesBinding
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MovieFragment : BaseFragment<FragmentMoviesBinding>(R.layout.fragment_movies) {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Movies"
        val navBar =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE

        setupRecyclerView()

        searchMovies()

        subscribeToObservers()

        movieAdapter.setItemClickListener {
            navigateToDetailMovie(it)
        }

    }

    private fun searchMovies() {
        binding.etSearchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieSearchFragment(query)
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
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
                    Toast.makeText(
                        context,
                        "Something mistakes \n ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    private fun navigateToDetailMovie(movie: Movie) {
        val bundle = Bundle().apply {
            putParcelable(DetailMovieFragment.MOVIE_TYPE, movie)
        }
        findNavController().navigate(
            R.id.action_movieFragment_to_detailFragment,
            bundle
        )

    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMovies.adapter = null
        _binding = null
    }

}