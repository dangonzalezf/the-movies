package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.domain.movie.datasource.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.movies
    }
}
