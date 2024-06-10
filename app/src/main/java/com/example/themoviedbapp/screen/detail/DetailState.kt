package com.example.themoviedbapp.screen.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember


/**
 * This class is an state holder of ui created
 * to provides necessary objects for DetailScreen.
 */
@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackBarHostState: SnackbarHostState
) {

    @Composable
    fun ShowMessageEffects(message: String?, onMessageShown: () -> Unit) {
        LaunchedEffect(message) {
            message?.let {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar(it)
                onMessageShown()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackBarHostState: SnackbarHostState = SnackbarHostState()
): DetailState {
    return remember(scrollBehavior, snackBarHostState) { DetailState(scrollBehavior = scrollBehavior, snackBarHostState = snackBarHostState) }
}
