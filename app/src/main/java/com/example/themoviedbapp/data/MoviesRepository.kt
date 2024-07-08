package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.datasource.MoviesLocalDataSource
import com.example.themoviedbapp.data.datasource.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

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

    fun fetchMovieById(id: Int): Flow<Movie?> = localDataSource.findMovieById(id).onEach { movie ->
        if(movie == null) {
            val remoteMovie = remoteDataSource.fetchMovieById(id)
            localDataSource.saveMovies(listOf(remoteMovie))
        }
    }


    suspend fun toggleFavorite(movie: Movie) {
        localDataSource.saveMovies(listOf(movie.copy(favorite = !movie.favorite)))
    }
}
