package com.rasyidin.moviesapp.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.moviesapp.core.ui.base.BaseFragment
import com.rasyidin.moviesapp.favorite.SectionPagerAdapter.Companion.TAB_TITLES
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

        val sectionPagerAdapter =
            SectionPagerAdapter(childFragmentManager, lifecycle)

        binding.apply {
            viewPagerFav.apply {
                adapter = sectionPagerAdapter
                offscreenPageLimit = 2
            }

        }

        TabLayoutMediator(binding.tabsFav, binding.viewPagerFav) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(TAB_TITLES[0])
                else -> getString(TAB_TITLES[1])
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}