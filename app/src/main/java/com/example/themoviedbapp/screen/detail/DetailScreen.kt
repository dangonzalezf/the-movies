package com.example.themoviedbapp.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.themoviedbapp.R
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.screen.LoadingIndicator
import com.example.themoviedbapp.screen.Property
import com.example.themoviedbapp.screen.Screen
import com.example.themoviedbapp.screen.TopBarNavigationButton
import com.example.themoviedbapp.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel = viewModel(), onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val detailState = rememberDetailState()
    
    detailState.ShowMessageEffects(message = state.message) {
        vm.onMessageShown()
    }
    
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = state.movie?.title ?: "") },
                    navigationIcon = {
                        TopBarNavigationButton(
                            icon = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = R.string.back,
                            clickAction = onBack
                        )
                    },
                    scrollBehavior = detailState.scrollBehavior
                )
            },
            floatingActionButton = {
                val favorite = state.movie?.favorite ?: false
                FloatingActionButton(onClick = { vm.onFavoriteClick() }) {
                    Icon(
                        imageVector = if(favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite_button)
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = detailState.snackBarHostState
                )
            }) { padding ->
            LoadingIndicator(state.loading, padding)
            MovieDetail(state.movie, padding)
        }
    }
}

@Composable
fun MovieDetail(movie: Movie?, padding: PaddingValues) {
    movie?.let {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            AsyncImage(
                model = it.backdrop,
                contentDescription = it.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)

            )
            Text(text = movie.overview, modifier = Modifier.padding(16.dp))
            Text(
                text = buildAnnotatedString {
                    Property(name = "Original Language", value = movie.originalLanguage)
                    Property(name = "Original Title", value = movie.originalTitle)
                    Property(name = "Release Date", value = movie.releaseDate)
                    Property(name = "Popularity", value = movie.popularity.toString())
                    Property(name = "Vote Average", value = movie.voteAverage.toString(), end = true)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp)
            )

        }
    }
}

