package com.rasyidin.moviesapp.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.favorite.databinding.FragmentFavoriteBinding
import com.rasyidin.moviesapp.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite"
        val navBar =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(com.rasyidin.moviesapp.R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE

        loadKoinModules(favoriteModule)
        val sectionPagerAdapter = SectionPagerAdapter(requireContext(), childFragmentManager)
        binding.viewPagerFav.adapter = sectionPagerAdapter
        binding.tabsFav.setupWithViewPager(binding.viewPagerFav)
    }
}