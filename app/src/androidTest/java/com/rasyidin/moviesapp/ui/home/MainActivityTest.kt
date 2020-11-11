package com.rasyidin.moviesapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.rasyidin.moviesapp.R
import com.rasyidin.moviesapp.utils.DataDummy
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTv = DataDummy.generateDummyTv()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyMovies[0].title)))

        onView(withId(R.id.tv_release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_detail)).check(matches(withText(dummyMovies[0].releaseDate)))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovies[0].score)))

        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(withText(dummyMovies[0].genre)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovies[0].overview)))

        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }

    @Test
    fun loadTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTv.size
            )
        )
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyTv[0].title)))

        onView(withId(R.id.tv_release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_detail)).check(matches(withText(dummyTv[0].release)))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyTv[0].score)))

        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(withText(dummyTv[0].genres)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTv[0].overview)))

        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
    }
}