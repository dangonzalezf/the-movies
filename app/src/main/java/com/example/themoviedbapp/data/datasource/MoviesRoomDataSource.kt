package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.data.database.DBMovie
import com.example.themoviedbapp.domain.Movie
import com.example.themoviedbapp.data.database.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>
    fun findMovieById(id: Int): Flow<Movie?>

    suspend fun isEmpty(): Boolean

    suspend fun saveMovies(movies: List<Movie>)
}

class MoviesRoomDataSource(private val moviesDao: MoviesDao) : MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> =
        moviesDao.fetchPopularMovies().map { movies -> movies.map { it.toDomainModel() } }

    override fun findMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).map { it?.toDomainModel() }

    override suspend fun isEmpty() = moviesDao.countMovies() == 0

    override suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(
        movies.map { movie -> movie.toDataBaseModel() }
    )

    private fun Movie.toDataBaseModel(): DBMovie {
        return DBMovie (
            id = id,
            title = title,
            poster = poster,
            backdrop = backdrop,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            voteCount = voteCount,
            overview = overview,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            popularity = popularity,
            favorite = favorite
        )
    }

    private fun DBMovie.toDomainModel(): Movie {
        return Movie(
            id = id,
            title = title,
            poster = poster,
            backdrop = backdrop,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            voteCount = voteCount,
            overview = overview,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            popularity = popularity,
            favorite = favorite
        )
    }
}
