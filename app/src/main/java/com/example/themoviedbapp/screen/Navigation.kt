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
import com.example.themoviedbapp.BuildConfig
import com.example.themoviedbapp.domain.movie.datasource.MoviesRepository
import com.example.themoviedbapp.domain.movie.usecases.FetchMoviesUseCase
import com.example.themoviedbapp.domain.movie.usecases.FindMovieByIdUseCase
import com.example.themoviedbapp.domain.movie.usecases.ToggleFavoriteUseCase
import com.example.themoviedbapp.domain.region.data.RegionRepository
import com.example.themoviedbapp.feature.detail.DetailScreen
import com.example.themoviedbapp.feature.detail.DetailViewModel
import com.example.themoviedbapp.feature.home.HomeScreen
import com.example.themoviedbapp.feature.home.HomeViewModel
import com.example.themoviedbapp.framework.core.MoviesClient
import com.example.themoviedbapp.framework.movie.database.MoviesRoomDataSource
import com.example.themoviedbapp.framework.movie.network.MoviesServerDataSource
import com.example.themoviedbapp.framework.region.GeocoderRegionDataSource
import com.example.themoviedbapp.framework.region.PlayServiceLocationDataSource
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
        remoteDataSource = MoviesServerDataSource(moviesService = MoviesClient(BuildConfig.TMDB_API_KEY).instance)
    )
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(route = NavScreen.Home.route) {
            HomeScreen(
                onClick = { movie ->
                    navController.navigate(NavScreen.Detail.createRoute(movie.id))
                },
                vm = viewModel {
                    HomeViewModel(
                        fetchMoviesUseCase = FetchMoviesUseCase(
                            moviesRepository
                        )
                    )
                }
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
