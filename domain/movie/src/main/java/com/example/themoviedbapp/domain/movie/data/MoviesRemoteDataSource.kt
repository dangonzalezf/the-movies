package com.example.themoviedbapp.domain.movie.data

import com.example.themoviedbapp.domain.movie.entities.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun fetchMovieById(id: Int): Movie
}
