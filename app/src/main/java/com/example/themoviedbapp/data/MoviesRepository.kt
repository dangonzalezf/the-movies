package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.datasource.MoviesRemoteDataSource

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesRemoteDataSource.fetchPopularMovies(regionRepository.findLastRegion())
    }

    suspend fun fetchMovieById(id: Int): Movie = moviesRemoteDataSource.fetchMovieById(id)
}
