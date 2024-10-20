package com.example.themoviedbapp.usecases

import com.example.themoviedbapp.domain.Movie
import com.example.themoviedbapp.data.MoviesRepository

class ToggleFavoriteUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        moviesRepository.toggleFavorite(movie)
    }
}
