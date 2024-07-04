package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())

    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            moviesRepository.fetchMovieById(movieId).collect { movie ->
            _state.value = UiState(loading = false, movie = movie)
            }
        }
    }

    fun onFavoriteClick() {
        _state.value.movie?.let { movie ->
            viewModelScope.launch {
                moviesRepository.toggleFavorite(movie)
            }
        }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )
}
