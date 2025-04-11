import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.themoviedbapp.feature.common.LOADING_INDICATOR_TAG
import com.example.themoviedbapp.feature.common.Result
import com.example.themoviedbapp.feature.home.HomeScreen
import com.example.themoviedbapp.sampleMovies
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(onClick = {}, state = Result.Loading)
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()

    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                onClick = {},
                state = Result.Error(Exception())
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_showMovies(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(onClick = {}, state = Result.Success(sampleMovies(1, 2, 3, 4)))
        }

        onNodeWithText("Movie 1").assertExists()
        onNodeWithText("Movie 2").assertExists()
        onNodeWithText("Movie 3").assertExists()
        onNodeWithText("Movie 4").assertExists()
        //onNodeWithText("Overview").assertExists()
    }

    @Test
    fun whenMovieCLick_listenerIsCall(): Unit = with(composeTestRule) {
        var clickedMovieId = -1
        val movies = sampleMovies(1, 2, 3)

        setContent {
            HomeScreen(
                onClick = { clickedMovieId = it.id },
                state = Result.Success(movies)
            )

        }

        onNodeWithText("Movie 2").performClick()
        assertEquals(2, clickedMovieId)
    }
}
