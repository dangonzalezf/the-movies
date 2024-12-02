package com.example.themoviedbapp.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.feature.common.Result
import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.feature.common.ifSuccess
import com.example.themoviedbapp.feature.common.stateAsResultIn
import com.example.themoviedbapp.domain.movie.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.domain.movie.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    movieId: Int,
    findMovieByIdUseCase: FindMovieByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<Movie>> = findMovieByIdUseCase(movieId)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess { movie ->
            viewModelScope.launch {
                toggleFavoriteUseCase.invoke(movie)
            }
        }
    }
}
