package com.example.themoviedbapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedbapp.domain.Movie

interface IMoviesDataBase {
    fun moviesDao(): MoviesDao }

@Database(entities = [DBMovie::class], version = 1, exportSchema = false)
abstract class MoviesDataBase: RoomDatabase(), IMoviesDataBase {
}
