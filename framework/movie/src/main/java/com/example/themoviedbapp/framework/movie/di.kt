package com.example.themoviedbapp.framework.movie

import com.example.themoviedbapp.domain.movie.data.MoviesLocalDataSource
import com.example.themoviedbapp.domain.movie.data.MoviesRemoteDataSource
import com.example.themoviedbapp.framework.movie.database.MoviesRoomDataSource
import com.example.themoviedbapp.framework.movie.network.MoviesServerDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkMovieModule = module {
    factoryOf(::MoviesRoomDataSource) bind MoviesLocalDataSource::class
    factoryOf(::MoviesServerDataSource) bind MoviesRemoteDataSource::class
}
