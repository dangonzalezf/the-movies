package com.example.themoviedbapp

import com.example.themoviedbapp.domain.movie.entities.Movie

fun sampleMovie(id: Int) = Movie(
    id = id,
    title = "Movie $id",
    poster = "poster$id",
    backdrop = "backdrop$id",
    releaseDate = "releaseDate$id",
    voteAverage = 0.0,
    voteCount = 0,
    overview = "overview$id",
    originalLanguage = "originalLanguage$id",
    originalTitle = "originalTitle$id",
    popularity = 0.0,
    favorite = false
)

fun sampleMovies(vararg ids: Int) = ids.map { sampleMovie(it) }
