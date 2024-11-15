package com.example.themoviedbapp.screen

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.themoviedbapp.App
import com.example.themoviedbapp.data.MoviesRepository
import com.example.themoviedbapp.data.RegionRepository
import com.example.themoviedbapp.framework.GeocoderRegionDataSource
import com.example.themoviedbapp.framework.MoviesRoomDataSource
import com.example.themoviedbapp.framework.MoviesServerDataSource
import com.example.themoviedbapp.framework.PlayServiceLocationDataSource
import com.example.themoviedbapp.framework.remote.MoviesClient
import com.example.themoviedbapp.screen.detail.DetailScreen
import com.example.themoviedbapp.screen.home.HomeScreen
import com.example.themoviedbapp.usecases.FetchMoviesUseCase
import com.example.themoviedbapp.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.usecases.ToggleFavoriteUseCase
import com.example.themoviedbapp.viewmodel.DetailViewModel
import com.example.themoviedbapp.viewmodel.HomeViewModel
import com.google.android.gms.location.LocationServices

sealed class NavScreen(val route: String) {
    data object Home : NavScreen("home")
    data object Detail : NavScreen("detail/{${NavArgs.MovieId.key}}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

enum class NavArgs(val key: String) {
    MovieId("movieId")
}

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val moviesRepository = MoviesRepository(
        regionRepository = RegionRepository(
            GeocoderRegionDataSource(
                Geocoder(app),
                PlayServiceLocationDataSource(LocationServices.getFusedLocationProviderClient(app))
            )
        ),
        localDataSource = MoviesRoomDataSource(app.db.moviesDao()),
        remoteDataSource = MoviesServerDataSource(MoviesClient.instance)
    )
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(route = NavScreen.Home.route) {
            HomeScreen(
                onClick = { movie ->
                    navController.navigate(NavScreen.Detail.createRoute(movie.id))
                },
                vm = viewModel { HomeViewModel(fetchMoviesUseCase = FetchMoviesUseCase(moviesRepository)) }
            )
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.MovieId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                vm = viewModel {
                    DetailViewModel(
                        movieId,
                        FindMovieByIdUseCase(moviesRepository),
                        ToggleFavoriteUseCase(moviesRepository)
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
