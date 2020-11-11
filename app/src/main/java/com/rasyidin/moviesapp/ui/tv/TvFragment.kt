package com.rasyidin.moviesapp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.moviesapp.R
import kotlinx.android.synthetic.main.fragment_tv.*

class TVFragment : Fragment() {

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
            val tvAdapter = TvAdapter()
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(TvViewModel::class.java)

            val tv = viewModel.getTv()
            if (tv.isEmpty()) {
                dataIsEmpty()
            }

            tvAdapter.setListTv(tv)

            with(rv_tv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    private fun dataIsEmpty() {
        rv_tv.visibility = View.GONE
        img_no_data.visibility = View.VISIBLE
    }

}