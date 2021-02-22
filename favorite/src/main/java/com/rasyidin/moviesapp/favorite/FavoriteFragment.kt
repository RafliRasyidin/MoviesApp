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

    private var mediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as AppCompatActivity).supportActionBar?.title = "Favorite"
            val navBar =
                (activity as AppCompatActivity).findViewById<BottomNavigationView>(com.rasyidin.moviesapp.R.id.bottomNavigationView)
            navBar.visibility = View.VISIBLE

            loadKoinModules(favoriteModule)

            initViewPager()

            initTabLayout()
        }

    }

    private fun initViewPager() {
        val sectionPagerAdapter =
            SectionPagerAdapter(childFragmentManager, lifecycle)

        binding.apply {
            viewPagerFav.apply {
                adapter = sectionPagerAdapter
                offscreenPageLimit = 2
            }

        }
    }

    private fun initTabLayout() {
        mediator = TabLayoutMediator(binding.tabsFav, binding.viewPagerFav) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(TAB_TITLES[0])
                else -> getString(TAB_TITLES[1])
            }
        }
        mediator?.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator?.detach()
        mediator = null
        binding.viewPagerFav.adapter = null
        _binding = null
    }
}