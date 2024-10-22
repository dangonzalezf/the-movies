package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun fetchMovieById(id: Int): Movie
}
