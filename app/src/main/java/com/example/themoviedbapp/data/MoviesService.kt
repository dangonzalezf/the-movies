package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.model.RemoteMovie
import com.example.themoviedbapp.data.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun fetchPopularMovies(@Query("region") region: String): RemoteResult

    @GET("movie/{id}")
    suspend fun fetchMovieById(@Path("id") id: Int): RemoteMovie
}
