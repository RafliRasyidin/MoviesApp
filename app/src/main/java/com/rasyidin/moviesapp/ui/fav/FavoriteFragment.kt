package com.rasyidin.moviesapp.ui.fav

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite"

        val sectionPagerAdapter = SectionPagerAdapter(requireContext(), childFragmentManager)
        binding.viewPagerFav.adapter = sectionPagerAdapter
        binding.tabsFav.setupWithViewPager(binding.viewPagerFav)
    }
}