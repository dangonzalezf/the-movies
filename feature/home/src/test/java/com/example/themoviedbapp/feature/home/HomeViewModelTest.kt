package com.example.themoviedbapp.feature.home

import app.cash.turbine.test
import com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase
import com.example.themoviedbapp.sampleMovies
import com.example.themoviedbapp.testrules.CoroutinesTestRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import com.example.themoviedbapp.feature.common.Result

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    @Mock
    lateinit var vm: HomeViewModel

    @Before
    fun setup() {
        vm = HomeViewModel(fetchMoviesUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Movies are not request if UI is no ready`() = runTest {
        // Given
        vm.state.first()

        // When
        runCurrent() // ejecuta las corrutinas pendientes

        // Then
        verify(fetchMoviesUseCase, times(0)).invoke()
    }

    @Test
    fun `Movies are requested if UI is Ready`(): Unit = runTest {
        // Given
        val movies = sampleMovies(1, 2, 3, 4)
        whenever(fetchMoviesUseCase()).thenReturn(flowOf(movies))

        // When
        vm.onUiReady()

        // Then
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(movies), awaitItem())
        }
    }

    @Test
    fun `Error is propagated when request fails`(): Unit = runTest {
        // Given
        val errorMessage = "Error"
        val exception = RuntimeException(errorMessage)
        whenever(fetchMoviesUseCase()).thenThrow(exception)

        // When
        vm.onUiReady()

        // Then
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).exception.message
            assertEquals(errorMessage, exceptionMessage)
        }
    }
}
