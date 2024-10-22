package com.example.themoviedbapp.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM DBMovie")
    fun fetchPopularMovies(): Flow<List<DBMovie>>

    @Query("SELECT * FROM DBMovie WHERE id = :id")
    fun findMovieById(id: Int): Flow<DBMovie?>

    @Query("SELECT COUNT(*) FROM DBMovie")
    suspend fun countMovies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<DBMovie>)
}
