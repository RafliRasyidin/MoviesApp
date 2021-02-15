package com.rasyidin.moviesapp.ui.tv

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
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.adapters.TvAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentTvBinding
import com.rasyidin.moviesapp.ui.detail.DetailTvFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class TVFragment : BaseFragment<FragmentTvBinding>(R.layout.fragment_tv) {

    private val viewModel: TvViewModel by viewModel()
    private lateinit var popularAdapter: TvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "TV Show"
        val navBar =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE

        setupRecyclerView()

        observePopular()

        popularAdapter.setItemClickListener { selectedData ->
            navigateToDetail(selectedData)
        }

        searchTv()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvTv.adapter = null
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvTv.apply {
        popularAdapter = TvAdapter()
        layoutManager = GridLayoutManager(context, 3)
        adapter = popularAdapter
        setHasFixedSize(true)
    }

    private fun navigateToDetail(tv: TV) {
        val bundle = Bundle().apply {
            putParcelable(DetailTvFragment.DETAIL_TV, tv)
        }
        findNavController().navigate(
            R.id.action_TVFragment_to_detailTvFragment,
            bundle
        )
    }

    private fun searchTv() {
        binding.etSearchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findNavController().navigate(
                    TVFragmentDirections.actionTVFragmentToSearchTvFragment(query)
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observePopular() {
        viewModel.getPopularTv().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> showProgressBar()
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { listTv ->
                        popularAdapter.setList(listTv)
                    }
                    popularAdapter.notifyDataSetChanged()

                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, "Something mistakes", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}