package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.database.MoviesDao

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    suspend fun fetchPopularMovies() = moviesDao.fetchPopularMovies()

    suspend fun findMovieById(id: Int) = moviesDao.findMovieById(id)

    suspend fun isEmpty() = moviesDao.countMovies() == 0

    suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies)
}
