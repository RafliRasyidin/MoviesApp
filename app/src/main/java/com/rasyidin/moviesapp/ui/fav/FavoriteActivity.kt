package com.rasyidin.moviesapp.ui.fav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rasyidin.moviesapp.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoviesApp)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.title = "Favorite"

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager_fav.adapter = sectionPagerAdapter
        tabs_fav.setupWithViewPager(view_pager_fav)
        supportActionBar?.elevation = 0f
    }
}