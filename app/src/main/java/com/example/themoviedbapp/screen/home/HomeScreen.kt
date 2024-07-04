package com.example.themoviedbapp.screen.home

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.themoviedbapp.data.Movie
import com.example.themoviedbapp.R
import com.example.themoviedbapp.common.PermissionRequestEffect
import com.example.themoviedbapp.screen.LoadingIndicator
import com.example.themoviedbapp.screen.Screen
import com.example.themoviedbapp.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Movie) -> Unit,
    vm: HomeViewModel
) {

    val homeState = rememberHomeState()
    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    scrollBehavior = homeState.scrollBehavior,
                )
            },
            modifier = Modifier.nestedScroll(
                homeState.scrollBehavior.nestedScrollConnection
            ),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding ->
            val state by vm.state.collectAsState()

            LoadingIndicator(loadingState = state.loading, padding = padding)
            LazyVerticalGrid(
                contentPadding = padding,
                columns = GridCells.Adaptive(minSize = 120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                items(state.movies, key = { it.id }) { movie ->
                    MovieItem(movie = movie, onClick = { onClick(movie) })
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Box {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(MaterialTheme.shapes.small)
            )
            if(movie.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.favorite_button),
                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.padding(8.dp).align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}
