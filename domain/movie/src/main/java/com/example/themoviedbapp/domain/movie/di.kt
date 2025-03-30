package com.example.themoviedbapp.domain.movie

import com.example.themoviedbapp.domain.movie.data.MoviesRepository
import com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase
import com.example.themoviedbapp.domain.movie.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.domain.movie.usecases.ToggleFavoriteUseCase
import com.example.themoviedbapp.domain.region.data.RegionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainMovieModule = module {
    factoryOf(::MoviesRepository)
    factoryOf(::FetchMoviesUseCase)
    factoryOf(::FindMovieByIdUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}
