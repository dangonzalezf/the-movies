package com.example.themoviedbapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedbapp.data.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movie")
    suspend fun fetchPopularMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun findMovieById(id: Int): Movie?

    @Query("SELECT COUNT(*) FROM Movie")
    suspend fun countMovies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)
}
