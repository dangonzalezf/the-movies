package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.model.toDomainModel

class MoviesRepository(private val regionRepository: RegionRepository) {

    suspend fun fetchPopularMovies(): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies(regionRepository.findLastRegion())
            .remoteMovies.map { it.toDomainModel() }

    suspend fun fetchMovieById(id: Int): Movie =
        MoviesClient
            .instance
            .fetchMovieById(id)
            .toDomainModel()
}
