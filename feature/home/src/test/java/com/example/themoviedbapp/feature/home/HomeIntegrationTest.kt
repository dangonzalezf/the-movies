package com.example.themoviedbapp.feature.home

import app.cash.turbine.test
import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase
import com.example.themoviedbapp.sampleMovies
import com.example.themoviedbapp.testrules.CoroutinesTestRule
import com.example.themoviedbapp.testrules.data.buildMoviesRepositoryWith
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import com.example.themoviedbapp.feature.common.Result

class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = sampleMovies(1, 2)
        val vm = HomeViewModel(
            FetchMoviesUseCase(buildMoviesRepositoryWith(remoteData = remoteData))
        )

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Movie>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local source when available`() = runTest {
        val localData = sampleMovies(1, 2)
        val vm = HomeViewModel(FetchMoviesUseCase(buildMoviesRepositoryWith(localData = localData)))

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }
}
