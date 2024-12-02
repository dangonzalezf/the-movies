package com.example.themoviedbapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.feature.common.Result
import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.feature.common.stateAsResultIn
import com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(private val fetchMoviesUseCase: com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<com.example.themoviedbapp.feature.common.Result<List<com.example.themoviedbapp.domain.movie.entities.Movie>>> = uiReady
        .filter { it } // No se ejecuta hasta que uiReady sea true
        .flatMapLatest { fetchMoviesUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }
}
