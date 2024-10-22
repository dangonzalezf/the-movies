package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>
    fun findMovieById(id: Int): Flow<Movie?>

    suspend fun isEmpty(): Boolean

    suspend fun saveMovies(movies: List<Movie>)
}
