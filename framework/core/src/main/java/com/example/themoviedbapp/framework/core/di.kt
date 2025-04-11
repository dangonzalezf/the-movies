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
object FrameworkCoreModule {

    @Provides
    fun providesMoviesDao(dataBase: MoviesDataBase) = dataBase.moviesDao()

    @Provides
    @Singleton
    fun providesMoviesService(
        @Named("apiKey") apiKey: String,
        @Named("apiUrl") apiUrl: String
    ) = MoviesClient(apiKey, apiUrl).instance

    @Module
    @InstallIn(SingletonComponent::class)
    object FrameworkCoreExtrasModule {

        @Provides
        @Singleton
        fun provideDataBase(app: Application) = Room.databaseBuilder(
            app,
            MoviesDataBase::class.java,
            "movie-db"
        ).build()

        @Provides
        @Singleton
        @Named("apiUrl")
        fun provideApiUrl(): String = "https://api.themoviedb.org/3/"
    }
}
