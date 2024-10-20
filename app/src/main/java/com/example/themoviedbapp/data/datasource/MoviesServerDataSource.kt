package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.domain.Movie
import com.example.themoviedbapp.data.remote.MoviesClient
import com.example.themoviedbapp.data.model.RemoteMovie
import com.example.themoviedbapp.data.remote.MoviesService

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun fetchMovieById(id: Int): Movie
}

class MoviesServerDataSource(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService
            .fetchPopularMovies(region)
            .remoteMovies.map { it.toDomainModel() }

    override suspend fun fetchMovieById(id: Int): Movie =
        moviesService
            .fetchMovieById(id)
            .toDomainModel()
}

private fun RemoteMovie.toDomainModel(): Movie =
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
