package com.example.themoviedbapp.framework.remote

import com.example.themoviedbapp.data.model.RemoteMovie
import com.example.themoviedbapp.domain.Movie

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
