package com.rasyidin.moviesapp.favorite.tv

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.adapters.FavTVAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.favorite.FavViewModel
import com.rasyidin.moviesapp.favorite.FavoriteFragmentDirections
import com.rasyidin.moviesapp.favorite.R
import com.rasyidin.moviesapp.favorite.databinding.FragmentFavoriteTVBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTVFragment : BaseFragment<FragmentFavoriteTVBinding>(R.layout.fragment_favorite_t_v) {

    private val viewModel: FavViewModel by viewModel()

    private val favTVAdapter by lazy { FavTVAdapter { navigateToDetail(it) } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavTv)

        setupRecyclerView()

        subscribeToObservers()

    }

    private fun navigateToDetail(tv: TV) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvFragment(tv)
        )
    }

    private fun setupRecyclerView() = binding.rvFavTv.apply {
        layoutManager = GridLayoutManager(context, 3)
        adapter = favTVAdapter
        setHasFixedSize(true)
    }

    private fun subscribeToObservers() {
        viewModel.favoriteTv.observe(viewLifecycleOwner, {
            favTVAdapter.submitList(it)
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tv = favTVAdapter.getSwipeData(swipedPosition)
                tv?.let { viewModel.setFavTv(tv, false) }
                val snackbar =
                    Snackbar.make(view as View, "Removed from favorite", Snackbar.LENGTH_LONG)

                snackbar.setAction("UNDO") {
                    tv?.let { viewModel.setFavTv(tv, true) }
                }
                snackbar.show()
            }
        }
    })

}