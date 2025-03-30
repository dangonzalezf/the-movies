package com.example.themoviedbapp.domain.movie.data

import com.example.themoviedbapp.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>
    fun findMovieById(id: Int): Flow<Movie?>

    suspend fun isEmpty(): Boolean

    suspend fun saveMovies(movies: List<Movie>)
}
