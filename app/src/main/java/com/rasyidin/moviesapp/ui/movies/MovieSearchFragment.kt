package com.rasyidin.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.data.vo.Resource
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.adapters.MovieAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentMovieSearchBinding
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
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
                loadingState()
                viewModel.shouldDebounce(false)
                lifecycleScope.launch {
                    viewModel.queryChannel.send(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                loadingState()
                viewModel.shouldDebounce(true)
                lifecycleScope.launch {
                    viewModel.queryChannel.send(newText)
                }
                return true
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSearch.adapter = null
        _binding = null
    }

    private fun subscribeToObserver() {
        viewModel.searchMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    loadingState()
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    searchAdapter.setList(it.data)
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.ivNotFound.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvSearch.visibility = View.GONE
                    binding.ivNotFound.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun navigateToDetailMovie(movie: Movie) {
        val bundle = Bundle().apply {
            putParcelable(DetailMovieFragment.MOVIE_TYPE, movie)
        }
        findNavController().navigate(
            R.id.action_movieSearchFragment_to_detailFragment,
            bundle
        )

    }

    private fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.ivNotFound.visibility = View.GONE
        binding.rvSearch.visibility = View.GONE
    }
}