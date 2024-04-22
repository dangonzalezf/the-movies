package com.example.themoviedbapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themoviedbapp.ui.theme.TheMovieDBAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MovieGrid()
                }
            }
        }
    }
}

@Composable
fun MovieGrid() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(movies) {
            MovieItem(movie = it)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .background(Color.Gray)
        )
        Text(text = movie.title, style = MaterialTheme.typography.bodySmall, maxLines = 1, modifier = Modifier.padding(8.dp))
    }
}

data class Movie(
    val id: Int,
    val title: String,
    val poster: String
)

val movies = (1..100).map {
    Movie(
        id = it,
        title = "Movie $it",
        poster = "http://www.movies.cl/id=$it"
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
    TheMovieDBAppTheme {
        Surface {
            MovieGrid()
        }
    }
}
