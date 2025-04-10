package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.sampleMovies
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchMoviesUseCaseTest {

    @Test
    fun `invoke calls repository`() {
        val movieFlow = flowOf(sampleMovies(1, 2, 3, 4))
        val useCase = FetchMoviesUseCase(mock {
            on { movies } doReturn movieFlow
        })

        val result = useCase()

        assertEquals(movieFlow, result)
    }
}
