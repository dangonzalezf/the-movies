package com.example.themoviedbapp.domain.movie.datasource

import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.domain.region.data.RegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {

    val movies: Flow<List<Movie>> = localDataSource.movies.onEach { localMovies ->
        if (localMovies.isEmpty()) {
            val region = regionRepository.findLastRegion()
            val movies = remoteDataSource.fetchPopularMovies(region)
            localDataSource.saveMovies(movies)
        }
    }

    fun findMovieById(id: Int): Flow<Movie> = localDataSource.findMovieById(id)
        .onEach { movie ->
            if (movie == null) {
                val remoteMovie = remoteDataSource.fetchMovieById(id)
                localDataSource.saveMovies(listOf(remoteMovie))
            }
        }.filterNotNull()

    suspend fun toggleFavorite(movie: Movie) {
        localDataSource.saveMovies(listOf(movie.copy(favorite = !movie.favorite)))
    }
}
