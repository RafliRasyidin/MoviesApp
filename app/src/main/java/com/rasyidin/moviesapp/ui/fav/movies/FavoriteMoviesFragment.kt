package com.rasyidin.moviesapp.ui.fav.movies

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.Movie
import com.rasyidin.moviesapp.core.ui.adapters.FavMovieAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentFavoriteMoviesBinding
import com.rasyidin.moviesapp.ui.detail.DetailMovieFragment
import com.rasyidin.moviesapp.ui.fav.FavViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment :
    BaseFragment<FragmentFavoriteMoviesBinding>(R.layout.fragment_favorite_movies) {

    private val viewModel: FavViewModel by viewModel()

    private val favMovieAdapter by lazy { FavMovieAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavMovies)

        setupRecyclerView()

        observeFavMovies()
    }

    private fun setupRecyclerView() = binding.rvFavMovies.apply {
        layoutManager = GridLayoutManager(context, 3)
        adapter = favMovieAdapter
        setHasFixedSize(true)
    }

    private fun navigateToDetail(movie: Movie) {
        val bundle = Bundle().apply {
            putSerializable(DetailMovieFragment.MOVIE_TYPE, movie)
            putInt(DetailMovieFragment.MOVIE_TYPE, DetailMovieFragment.FAVORITE_MOVIE_KEY)
        }
        findNavController().navigate(
            R.id.action_favoriteFragment_to_detailFragment,
            bundle
        )

    }

    private fun observeFavMovies() {
        viewModel.getFavMovies().observe(viewLifecycleOwner, {
            favMovieAdapter.submitList(it)
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedPosition = viewHolder.adapterPosition
            val movie = favMovieAdapter.getSwipeData(swipedPosition)
            movie?.let { viewModel.setFavMovie(movie, false) }
            val snackbar =
                Snackbar.make(view as View, "Removed from favorite", Snackbar.LENGTH_LONG)

            snackbar.setAction("UNDO") {
                movie?.let { viewModel.setFavMovie(movie, true) }
            }
            snackbar.show()
        }
    })

}