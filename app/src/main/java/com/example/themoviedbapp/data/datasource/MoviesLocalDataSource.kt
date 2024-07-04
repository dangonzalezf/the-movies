package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.database.MoviesDao

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    val movies = moviesDao.fetchPopularMovies()

    fun findMovieById(id: Int) = moviesDao.findMovieById(id)

    suspend fun isEmpty() = moviesDao.countMovies() == 0

    suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies)
}
