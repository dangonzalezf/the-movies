package com.example.themoviedbapp.framework.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun providesDataBase(app: Application) = Room.databaseBuilder(app, MoviesDataBase::class.java, "movies-db").build()

    @Provides
    fun providesMoviesDao(dataBase: MoviesDataBase) = dataBase.moviesDao()

    @Provides
    @Singleton
    fun providesMoviesClient(@Named("apiKey") apiKey: String) = MoviesClient(apiKey).instance
}
