package com.rasyidin.moviesapp.ui.tv

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun testGetTv() {
        val tv = viewModel.getTv()
        assertNotNull(tv)
        assertEquals(10, tv.size)
    }
}