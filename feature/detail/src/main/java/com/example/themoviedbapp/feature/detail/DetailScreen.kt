package com.example.themoviedbapp.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.themoviedbapp.feature.common.AcScaffold
import com.example.themoviedbapp.feature.common.Property
import com.example.themoviedbapp.feature.common.Screen
import com.example.themoviedbapp.feature.common.R as CommonR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel = hiltViewModel(), onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val detailState = rememberDetailState(state)

    Screen {
        AcScaffold(
            state = state,
            topBar = {
                DetailTopBar(
                    title = detailState.topBarTitle,
                    scrollBehavior = detailState.scrollBehavior,
                    onBack = onBack
                )
            },
            floatingActionButton = {
                val favorite = detailState.movie?.favorite ?: false
                FloatingActionButton(onClick = { vm.onFavoriteClick() }) {
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = CommonR.string.favorite_button)
                    )
                }
            },
            snackBarHost = { SnackbarHost(hostState = detailState.snackbarHostState) },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding, movies ->
            MovieDetail(movies, Modifier.padding(padding))
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = CommonR.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun MovieDetail(
    movie: com.example.themoviedbapp.domain.movie.entities.Movie?,
    modifier: Modifier = Modifier
) {
    movie?.let {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
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

