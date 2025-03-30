package com.example.themoviedbapp.framework.movie.database

import com.example.themoviedbapp.domain.movie.data.MoviesLocalDataSource
import com.example.themoviedbapp.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class MoviesRoomDataSource(private val moviesDao: MoviesDao) : MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> =
        moviesDao.fetchPopularMovies().map { movies -> movies.map { it.toDomainModel() } }

    override fun findMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).map { it?.toDomainModel() }

    override suspend fun isEmpty() = moviesDao.countMovies() == 0

    override suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(
        movies.map { movie -> movie.toDataBaseModel() }
    )

    private fun Movie.toDataBaseModel(): DBMovie {
        return DBMovie(
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
