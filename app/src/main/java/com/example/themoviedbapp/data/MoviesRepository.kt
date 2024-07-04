package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.datasource.MoviesLocalDataSource
import com.example.themoviedbapp.data.datasource.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {

    val movies: Flow<List<Movie>> = localDataSource.movies.transform { localMovies ->
        val movies = localMovies.takeIf { it.isNotEmpty() }
            ?: remoteDataSource.fetchPopularMovies(regionRepository.findLastRegion()).also {
            localDataSource.saveMovies(it)
        }
        emit(movies)
    }

    suspend fun fetchMovieById(id: Int): Flow<Movie> = localDataSource.findMovieById(id).transform { localMovie ->
        val movie = localMovie ?: remoteDataSource.fetchMovieById(id).also { movie ->
            localDataSource.saveMovies(listOf(movie))
        }
        emit(movie)
    }

    suspend fun toggleFavorite(movie: Movie) {
        localDataSource.saveMovies(listOf(movie.copy(favorite = !movie.favorite)))
    }
}
