package com.example.themoviedbapp.framework.movie

import com.example.themoviedbapp.domain.movie.data.MoviesLocalDataSource
import com.example.themoviedbapp.domain.movie.data.MoviesRemoteDataSource
import com.example.themoviedbapp.framework.movie.database.MoviesRoomDataSource
import com.example.themoviedbapp.framework.movie.network.MoviesServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkMovieModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: MoviesRoomDataSource): MoviesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MoviesServerDataSource): MoviesRemoteDataSource
}
