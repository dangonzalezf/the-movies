package com.example.themoviedbapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.themoviedbapp.R
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel = viewModel(), onBack: () -> Unit) {
    val state = viewModel.state

    Screen {
        state.movie?.let { movie ->
            TopBarScreen(
                scrollBehavior = null,
                title = movie.title,
                navigationButton = {
                    TopBarNavigationButton(
                        icon = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = R.string.back,
                        clickAction = (onBack)
                    )
                }
            )
            { padding ->
                if (state.loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                MovieDetail(movie)
            }
        }
    }
}

@Composable
fun MovieDetail(movie: Movie) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()

            )
        }
        Column(modifier = Modifier.padding(12.dp)) {
            MovieTitle(movie)
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = movie.voteCount.toString())
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = movie.voteAverage.toString())
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
            Text(text = movie.overview)

        }
    }
}

@Composable
fun MovieTitle(movie: Movie) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.Black), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = movie.releaseDate)
    }
}
