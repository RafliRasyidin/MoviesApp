package com.rasyidin.moviesapp.favorite

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.favorite.movies.FavoriteMoviesFragment
import com.rasyidin.moviesapp.favorite.tv.FavoriteTVFragment

class SectionPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager,
        lifecycle
    ) {
    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv)
    }

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMoviesFragment()
            else -> FavoriteTVFragment()
        }
    }
}