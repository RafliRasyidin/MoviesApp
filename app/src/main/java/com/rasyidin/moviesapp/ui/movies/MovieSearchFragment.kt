package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.adapters.MovieAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentMovieSearchBinding
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MovieSearchFragment :
    BaseFragment<FragmentMovieSearchBinding>(R.layout.fragment_movie_search) {

    private lateinit var searchAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModel()
    private val args: MovieSearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Search Movie"

        searchMovies()

        subscribeToObserver()

        binding.etSearchMovie.setQuery(args.search, true)

        setupRecyclerView()

        searchAdapter.setItemClickListener {
            navigateToDetailMovie(it)
        }

    }

    private fun setupRecyclerView() = binding.rvSearch.apply {
        searchAdapter = MovieAdapter()
        layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = searchAdapter
        setHasFixedSize(true)
    }

    private fun searchMovies() {
        binding.etSearchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


    }

    private fun subscribeToObserver() {
        viewModel.search.observe(viewLifecycleOwner) { movies ->
            searchAdapter.setList(movies)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun navigateToDetailMovie(movie: Movie) {
        val bundle = Bundle().apply {
            putSerializable(DetailMovieFragment.MOVIE_TYPE, movie)
        }
        findNavController().navigate(
            R.id.action_movieSearchFragment_to_detailFragment,
            bundle
        )

    }


}