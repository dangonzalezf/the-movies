package com.example.themoviedbapp.usecases

import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.movies
    }
}
