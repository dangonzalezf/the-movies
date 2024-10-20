package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.Result
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.ifSuccess
import com.example.themoviedbapp.stateAsResultIn
import com.example.themoviedbapp.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.usecases.ToggleFavoriteUseCase
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
