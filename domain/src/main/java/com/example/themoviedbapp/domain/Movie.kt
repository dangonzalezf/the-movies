package com.example.themoviedbapp.domain

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val favorite: Boolean = false
)
