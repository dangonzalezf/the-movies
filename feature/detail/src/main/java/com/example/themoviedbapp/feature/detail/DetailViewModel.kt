package com.example.themoviedbapp.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbapp.feature.common.Result
import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.feature.common.ifSuccess
import com.example.themoviedbapp.feature.common.stateAsResultIn
import com.example.themoviedbapp.domain.movie.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.domain.movie.usecases.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("movieId") movieId: Int,
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
