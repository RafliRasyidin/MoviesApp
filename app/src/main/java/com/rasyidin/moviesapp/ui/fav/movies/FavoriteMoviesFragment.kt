package com.rasyidin.moviesapp.ui.fav.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.ui.ViewModelFactory
import com.rasyidin.moviesapp.ui.fav.FavViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FavoriteMoviesFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private lateinit var viewModel: FavViewModel
    private lateinit var favMovieAdapter: FavMovieAdapter
    private val viewModelFactory: ViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_fav_movies)
        if (activity != null) {
            favMovieAdapter = FavMovieAdapter()
            viewModel = ViewModelProvider(this, viewModelFactory).get(FavViewModel::class.java)

            observeFavMovies()

            with(rv_fav_movies) {
                layoutManager = LinearLayoutManager(context)
                adapter = favMovieAdapter
                setHasFixedSize(true)
            }
        }
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
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedPosition = viewHolder.adapterPosition
            val movie = favMovieAdapter.getSwipeData(swipedPosition)
            movie?.let { viewModel.removeFavMovie(movie) }

            val snackbar =
                Snackbar.make(view as View, "Removed from favorite", Snackbar.LENGTH_LONG)
            snackbar.setAction("UNDO") {
                movie?.let { viewModel.setFavMovie(movie) }
            }
            snackbar.show()
        }
    })

}