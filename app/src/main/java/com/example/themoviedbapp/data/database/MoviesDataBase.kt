package com.example.themoviedbapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedbapp.data.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
