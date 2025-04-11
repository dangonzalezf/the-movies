package com.example.themoviedbapp.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedbapp.framework.movie.database.DBMovie
import com.example.themoviedbapp.framework.movie.database.MoviesDao

@Database(entities = [DBMovie::class], version = 1, exportSchema = false)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
