package com.example.themoviedbapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
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
