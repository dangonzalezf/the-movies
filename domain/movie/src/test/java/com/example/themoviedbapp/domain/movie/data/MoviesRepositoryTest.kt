package com.example.themoviedbapp.domain.movie.data

import com.example.themoviedbapp.domain.region.data.DEFAULT_REGION
import com.example.themoviedbapp.domain.region.data.RegionRepository
import com.example.themoviedbapp.sampleMovie
import com.example.themoviedbapp.sampleMovies
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var regionRepository: RegionRepository

    @Mock
    lateinit var localDataSource: MoviesLocalDataSource

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    private lateinit var repository: MoviesRepository

    private val remoteMovies = sampleMovies(1, 2)
    private val localMovies = sampleMovies(1, 2, 3)

    @Before
    fun setup() {
        repository = MoviesRepository(
            regionRepository = regionRepository,
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `popular movies are taken from local data source if available`() = runBlocking {
        // Given
        whenever(localDataSource.movies).thenReturn(flowOf(localMovies))

        // When
        val result = repository.movies

        // Then
        assertEquals(localMovies, result.first())
    }

    @Test
    fun `Popular movies are saved to local data source when it's empty`(): Unit = runBlocking {
        // Given
        whenever(localDataSource.movies).thenReturn(flowOf(emptyList()))
        whenever(regionRepository.findLastRegion()).thenReturn(DEFAULT_REGION)
        whenever(remoteDataSource.fetchPopularMovies(DEFAULT_REGION)).thenReturn(remoteMovies)

        // When
        repository.movies.first()

        // Then
        verify(localDataSource).saveMovies(remoteMovies)
    }

    @Test
    fun `Finding a movie by id is done in local data source`(): Unit = runBlocking {
        // Given
        val movieId = 1
        whenever(localDataSource.findMovieById(movieId)).thenReturn(flowOf(localMovies.first { it.id == movieId }))

        // When
        val result = repository.findMovieById(movieId)

        // Then
        assertEquals(localMovies.first { it.id == movieId }, result.first())
    }


    @Test
    fun `Finding a movie by id in local is null should not fetch movie in remote data source`(): Unit = runBlocking {
        // Given
        val movieId = 1
        whenever(localDataSource.findMovieById(movieId)).thenReturn(flowOf(null))

        // When
        repository.findMovieById(movieId)

        // Then
        verify(remoteDataSource, times(0)).fetchMovieById(movieId)
    }

    @Test
    fun `Toggling favorite updates local data source`(): Unit = runBlocking {
        val movie = sampleMovie(3)
        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies(argThat { get(0).id == 3 })
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(favorite = false)
        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies(argThat { get(0).favorite })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(favorite = true)
        repository.toggleFavorite(movie)

        verify(localDataSource).saveMovies(argThat { !get(0).favorite })
    }


}
