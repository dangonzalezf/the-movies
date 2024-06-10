package com.example.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.data.MoviesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val movieId: Int) : ViewModel() {

    private val repository = MoviesRepository()
    private val _state = MutableStateFlow(UiState())

    val state: StateFlow<UiState> = _state.asStateFlow()

    // Channel: first way to share ui state that is execute once time. @Property events & _events
    //private val _events = Channel<UiEvent>()
    //val events: Flow<UiEvent> = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, repository.fetchMovieById(movieId))
        }
    }
    fun onFavoriteClick() {
        //_events.trySend(UiEvent.ShowMessage("Favorite clicked"))
        _state.update { it.copy(message = "Favorite clicked") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }

    // Second way to share ui state that is execute once time
    /*sealed interface UiEvent {
        data class ShowMessage(val message: String) : UiEvent
    }*/

    // @param message.
    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )


}
