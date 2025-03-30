package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.domain.movie.data.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class FetchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.movies
    }
}
