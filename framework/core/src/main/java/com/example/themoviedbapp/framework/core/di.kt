package com.example.themoviedbapp.framework.core

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module

val frameworkCoreModule = module {
    single { Room.databaseBuilder(get(), MoviesDataBase::class.java, "movies-db").build() }
    factory { get<MoviesDataBase>().moviesDao() }
    single { MoviesClient(get(named("apiKey"))).instance }
}
