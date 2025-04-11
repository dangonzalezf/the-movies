package com.example.themoviedbapp

import androidx.test.rule.GrantPermissionRule
import com.example.themoviedbapp.framework.movie.database.DBMovie
import com.example.themoviedbapp.framework.movie.database.MoviesDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @Inject
    lateinit var moviesDao: MoviesDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun check_4_items_db() = runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1, 2, 3, 4))
        val movies = moviesDao.fetchPopularMovies().first()
        assertEquals(4, movies.size)
    }

    @Test
    fun check_6_items_db() = runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1, 2, 3, 4, 5, 6))
        assertEquals(6, moviesDao.fetchPopularMovies().first().size)
    }
}

fun buildDatabaseMovies(vararg id: Int) = id.map {
    DBMovie(
        id = it,
        title = "Movie $it",
        overview = "Overview $it",
        releaseDate = "01/01/2025",
        poster = "",
        backdrop = "",
        originalLanguage = "EN",
        originalTitle = "Original Title $it",
        popularity = 5.0,
        voteAverage = 5.1,
        voteCount = 22145,
        favorite = false,
    )
}
