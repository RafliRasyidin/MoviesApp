package com.rasyidin.moviesapp.ui.fav.tv

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.databinding.FragmentFavoriteTVBinding
import com.rasyidin.moviesapp.ui.base.BaseFragment
import com.rasyidin.moviesapp.ui.detail.DetailTvFragment
import com.rasyidin.moviesapp.ui.fav.FavViewModel
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
        val bundle = Bundle().apply {
            putSerializable(DetailTvFragment.DETAIL_TV, tv)
        }
        findNavController().navigate(
            R.id.action_favoriteFragment_to_detailTvFragment,
            bundle
        )
    }

    private fun setupRecyclerView() = binding.rvFavTv.apply {
        layoutManager = GridLayoutManager(context, 3)
        adapter = favTVAdapter
        setHasFixedSize(true)
    }

    private fun subscribeToObservers() {
        viewModel.getFavTv().observe(viewLifecycleOwner, {
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