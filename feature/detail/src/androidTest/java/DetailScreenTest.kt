import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.themoviedbapp.feature.common.LOADING_INDICATOR_TAG
import com.example.themoviedbapp.feature.common.Result
import com.example.themoviedbapp.feature.detail.BACK_BUTTON_TAG
import com.example.themoviedbapp.feature.detail.DetailScreen
import com.example.themoviedbapp.feature.detail.FAVORITE_BUTTON_TAG
import com.example.themoviedbapp.sampleMovie
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgressIndicator(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Loading,
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Error(Exception()),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_movieIsShown(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("Movie 2").assertIsDisplayed()
        onNodeWithText("Movie 2").assertExists()
    }

    @Test
    fun whenFavoriteIsClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var isFavoriteClicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = {},
                onFavoriteClicked = { isFavoriteClicked = true }
            )
        }

        onNodeWithTag(FAVORITE_BUTTON_TAG).performClick()

        assertTrue(isFavoriteClicked)
    }

    @Test
    fun whenOnBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var isBackClicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = { isBackClicked = true },
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag(BACK_BUTTON_TAG).performClick()
        assertTrue(isBackClicked)
    }
}
