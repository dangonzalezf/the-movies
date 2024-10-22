package com.example.themoviedbapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

interface IMoviesDataBase {
    fun moviesDao(): MoviesDao
}

@Database(entities = [DBMovie::class], version = 1, exportSchema = false)
abstract class MoviesDataBase: RoomDatabase(), IMoviesDataBase {
}
