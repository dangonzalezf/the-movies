package com.example.themoviedbapp.testrules.data

import com.example.themoviedbapp.domain.movie.data.MoviesLocalDataSource
import com.example.themoviedbapp.domain.movie.data.MoviesRemoteDataSource
import com.example.themoviedbapp.domain.movie.data.MoviesRepository
import com.example.themoviedbapp.domain.movie.entities.Movie
import com.example.themoviedbapp.domain.region.data.DEFAULT_REGION
import com.example.themoviedbapp.domain.region.data.RegionDataSource
import com.example.themoviedbapp.domain.region.data.RegionRepository
import com.example.themoviedbapp.domain.region.entities.Location
import com.example.themoviedbapp.sampleMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

fun buildMoviesRepositoryWith(
    localData: List<Movie> = emptyList(),
    remoteData: List<Movie> = emptyList()
): MoviesRepository {
    val regionRepository = RegionRepository(FakeRegionDataSource())

    val localDataSource = FakeLocalDataSource().apply { inMemoryMovies.value = localData }

    val remoteDataSource = FakeRemoteDataSource().apply { movies = remoteData }

    return MoviesRepository(regionRepository, localDataSource, remoteDataSource)
}

class FakeRegionDataSource : RegionDataSource {

    val region: String = DEFAULT_REGION

    override suspend fun findLastRegion(): String = region

    override suspend fun Location.toRegion(): String = region
}

class FakeLocalDataSource : MoviesLocalDataSource {

    val inMemoryMovies = MutableStateFlow<List<Movie>>(emptyList())

    override val movies: Flow<List<Movie>> = inMemoryMovies

    override fun findMovieById(id: Int): Flow<Movie?> =
        inMemoryMovies.map { it.firstOrNull { movie -> movie.id == id } }

    override suspend fun isEmpty(): Boolean = movies.toList().isNotEmpty()

    override suspend fun saveMovies(movies: List<Movie>) {
        inMemoryMovies.value = movies
    }
}

class FakeRemoteDataSource : MoviesRemoteDataSource {
    var movies = sampleMovies(1, 2, 3, 4)

    override suspend fun fetchPopularMovies(region: String): List<Movie> = movies

    override suspend fun fetchMovieById(id: Int): Movie = movies.first { it.id == id }
}
