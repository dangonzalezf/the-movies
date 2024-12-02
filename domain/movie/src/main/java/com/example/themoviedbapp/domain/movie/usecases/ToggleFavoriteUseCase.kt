package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.domain.movie.datasource.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie

class ToggleFavoriteUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        moviesRepository.toggleFavorite(movie)
    }
}
