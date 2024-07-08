package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    movieId: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val state: StateFlow<UiState> = moviesRepository.fetchMovieById(movieId)
        .map { movie -> UiState(movie = movie) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    fun onFavoriteClick() {
        state.value.movie?.let { movie ->
            viewModelScope.launch {
                moviesRepository.toggleFavorite(movie)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )
}
