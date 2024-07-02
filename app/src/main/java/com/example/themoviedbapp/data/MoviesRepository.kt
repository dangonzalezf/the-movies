package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.datasource.MoviesLocalDataSource
import com.example.themoviedbapp.data.datasource.MoviesRemoteDataSource

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {

    suspend fun fetchPopularMovies(): List<Movie> {
        if (localDataSource.fetchPopularMovies().isEmpty()) {
            val region = regionRepository.findLastRegion()
            val movies = remoteDataSource.fetchPopularMovies(region)
            localDataSource.saveMovies(movies)
        }
        return localDataSource.fetchPopularMovies()
    }

    suspend fun fetchMovieById(id: Int): Movie {
        if (localDataSource.findMovieById(id) == null) {
            val movie = remoteDataSource.fetchMovieById(id)
            localDataSource.saveMovies(listOf(movie))
        }
        return checkNotNull(localDataSource.findMovieById(id))
    }
}
