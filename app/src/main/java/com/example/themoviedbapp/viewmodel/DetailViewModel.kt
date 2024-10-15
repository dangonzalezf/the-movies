package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.Result
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import com.example.themoviedbapp.ifSuccess
import com.example.themoviedbapp.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    movieId: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val state: StateFlow<Result<Movie>> = moviesRepository.findMovieById(movieId)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClick() {
        state.value.ifSuccess { movie ->
            viewModelScope.launch {
                moviesRepository.toggleFavorite(movie)
            }
        }
    }
}
