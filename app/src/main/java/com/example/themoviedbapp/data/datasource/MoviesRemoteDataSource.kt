package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.remote.MoviesClient
import com.example.themoviedbapp.data.model.RemoteMovie

class MoviesRemoteDataSource {

    suspend fun fetchPopularMovies(region: String): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies(region)
            .remoteMovies.map { it.toDomainModel() }

    suspend fun fetchMovieById(id: Int): Movie =
        MoviesClient
            .instance
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
