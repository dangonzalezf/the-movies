package com.example.themoviedbapp.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.example.themoviedbapp.feature.common.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("movieId")
    fun provideMovieId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle[NavArgs.MovieId.key] ?: throw IllegalStateException("Movie Id not found in the state handle")
    }
}
