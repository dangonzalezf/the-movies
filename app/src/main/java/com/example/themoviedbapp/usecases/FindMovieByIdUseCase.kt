package com.example.themoviedbapp.usecases

import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke (id: Int): Flow<Movie> {
        return moviesRepository.findMovieById(id)
    }
}
