package com.rasyidin.moviesapp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.data.vo.StatusResponse
import com.rasyidin.moviesapp.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TVFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private lateinit var viewModel: TvViewModel
    private lateinit var tvAdapter: TvAdapter
    private val viewModelFactory: ViewModelFactory by instance()

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
        viewModel.getTv().observe(viewLifecycleOwner, {
            when (it.status) {
                StatusResponse.LOADING -> showProgressBar()
                StatusResponse.SUCCESS -> {
                    hideProgressBar()
                    tvAdapter.setListTv(it.body)
                    tvAdapter.notifyDataSetChanged()

                }
                StatusResponse.ERROR -> {
                    hideProgressBar()
                    Toast.makeText(context, "Something mistakes", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}