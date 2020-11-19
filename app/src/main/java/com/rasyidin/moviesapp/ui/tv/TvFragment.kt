package com.rasyidin.moviesapp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.remote.tv.TV
import com.rasyidin.moviesapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TVFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: TvViewModel
    private lateinit var tvAdapter: TvAdapter
    override val kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private var tv: MutableList<TV> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            tvAdapter = TvAdapter()
            viewModel = ViewModelProvider(
                this,
                viewModelFactory
            ).get(TvViewModel::class.java)

            observeTv()

            with(rv_tv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    private fun observeTv() {
        showProgressBar()
        viewModel.getTv().observe(viewLifecycleOwner, {
            tv.addAll(it)
            tvAdapter.setListTv(tv)
            tvAdapter.notifyDataSetChanged()
            hideProgressBar()
        })
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}