package com.example.themoviedbapp.domain.movie.usecases

import com.example.themoviedbapp.domain.movie.data.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie
import org.koin.core.annotation.Factory

@Factory
class ToggleFavoriteUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        moviesRepository.toggleFavorite(movie)
    }
}
