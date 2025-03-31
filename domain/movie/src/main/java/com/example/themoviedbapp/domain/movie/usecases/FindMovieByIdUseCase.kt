package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.domain.movie.data.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    operator fun invoke (id: Int): Flow<Movie> {
        return moviesRepository.findMovieById(id)
    }
}
