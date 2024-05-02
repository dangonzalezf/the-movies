package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.model.RemoteMovie
import com.example.themoviedbapp.data.model.RemoteResult

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String
)
