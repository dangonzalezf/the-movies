package com.example.themoviedbapp.feature.detail

import com.example.themoviedbapp.sampleMovies
import com.example.themoviedbapp.testrules.CoroutinesTestRule
import com.example.themoviedbapp.testrules.data.buildMoviesRepositoryWith
import org.junit.Before
import org.junit.Rule

class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setup() {

    }
}
