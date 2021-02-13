package com.rasyidin.moviesapp.ui.tv

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
import com.rasyidin.moviesapp.core.domain.model.TV
import com.rasyidin.moviesapp.core.ui.adapters.TvAdapter
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentTvSearchBinding
import com.rasyidin.moviesapp.ui.detail.DetailTvFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchTvFragment : BaseFragment<FragmentTvSearchBinding>(R.layout.fragment_tv_search) {

    private val viewModel: TvViewModel by viewModel()
    private lateinit var searchAdapter: TvAdapter
    private val args: SearchTvFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Search TV Show"

        subscribeToObserver()

        searchTv()

        setupRecyclerView()

        binding.etSearchTv.setQuery(args.search, true)

        searchAdapter.setItemClickListener {
            navigateToDetail(it)
        }
    }

    private fun searchTv() {
        binding.etSearchTv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun setupRecyclerView() = binding.rvSearch.apply {
        searchAdapter = TvAdapter()
        layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = searchAdapter
        setHasFixedSize(true)
    }

    private fun subscribeToObserver() {
        viewModel.searchTv.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    searchAdapter.setList(it.data)
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.ivNotFound.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    loadingState()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvSearch.visibility = View.GONE
                    binding.ivNotFound.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun navigateToDetail(tv: TV) {
        val bundle = Bundle().apply {
            putParcelable(DetailTvFragment.DETAIL_TV, tv)
        }
        findNavController().navigate(
            R.id.action_searchTvFragment_to_detailTvFragment,
            bundle
        )
    }

    private fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.ivNotFound.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }
}