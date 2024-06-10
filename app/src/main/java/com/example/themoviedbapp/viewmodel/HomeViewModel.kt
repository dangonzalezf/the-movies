package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    private val moviesRepository = MoviesRepository()

    fun onUiReady(region: String) {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, movies = moviesRepository.fetchPopularMovies(region))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
    )
}
