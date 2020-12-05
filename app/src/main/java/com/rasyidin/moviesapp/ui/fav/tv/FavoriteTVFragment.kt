package com.rasyidin.moviesapp.ui.fav.tv

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
import kotlinx.android.synthetic.main.fragment_favorite_t_v.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FavoriteTVFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private lateinit var viewModel: FavViewModel
    private lateinit var favTVAdapter: FavTVAdapter
    private val viewModelFactory: ViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_t_v, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_fav_tv)
        if (activity != null) {
            favTVAdapter = FavTVAdapter()
            viewModel = ViewModelProvider(this, viewModelFactory).get(FavViewModel::class.java)
            observeFavTv()
            with(rv_fav_tv) {
                layoutManager = LinearLayoutManager(context)
                adapter = favTVAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun observeFavTv() {
        viewModel.getFavTv().observe(viewLifecycleOwner, {
            favTVAdapter.submitList(it)
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
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tv = favTVAdapter.getSwipeData(swipedPosition)
                tv?.let { viewModel.removeFavTv(tv) }

                val snackbar =
                    Snackbar.make(view as View, "Removed from favorite", Snackbar.LENGTH_LONG)
                snackbar.setAction("UNDO") {
                    tv?.let { viewModel.setFavTv(tv) }
                }
                snackbar.show()
            }
        }
    })

}