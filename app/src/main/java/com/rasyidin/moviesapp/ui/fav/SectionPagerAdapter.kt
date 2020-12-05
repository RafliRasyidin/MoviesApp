package com.rasyidin.moviesapp.ui.fav

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.ui.fav.movies.FavoriteMoviesFragment
import com.rasyidin.moviesapp.ui.fav.tv.FavoriteTVFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMoviesFragment()
            1 -> FavoriteTVFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(TAB_TITLES[position])

}