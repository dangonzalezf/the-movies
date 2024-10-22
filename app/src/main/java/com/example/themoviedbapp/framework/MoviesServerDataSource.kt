package com.example.themoviedbapp.framework

import com.example.themoviedbapp.data.datasource.MoviesRemoteDataSource
import com.example.themoviedbapp.domain.Movie
import com.example.themoviedbapp.framework.remote.MoviesService
import com.example.themoviedbapp.framework.remote.toDomainModel

class MoviesServerDataSource(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService
            .fetchPopularMovies(region)
            .remoteMovies.map { it.toDomainModel() }

    override suspend fun fetchMovieById(id: Int): Movie =
        moviesService
            .fetchMovieById(id)
            .toDomainModel()
}
