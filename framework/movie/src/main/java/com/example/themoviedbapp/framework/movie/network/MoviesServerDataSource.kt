package com.example.themoviedbapp.framework.movie.network

import com.example.themoviedbapp.domain.movie.data.MoviesRemoteDataSource
import com.example.themoviedbapp.domain.movie.entities.Movie
import org.koin.core.annotation.Factory

@Factory
internal class MoviesServerDataSource(private val moviesService: MoviesService) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService
            .fetchPopularMovies(region)
            .remoteMovies.map { it.toDomainModel() }

    override suspend fun fetchMovieById(id: Int): Movie =
        moviesService
            .fetchMovieById(id)
            .toDomainModel()
}

fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
        backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        favorite = false
    )
