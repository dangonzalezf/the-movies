package com.example.themoviedbapp.screen.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * This class is an state holder of ui created
 * to provides necessary objects for HomeScreen.
 */
@OptIn(ExperimentalMaterial3Api::class)
class HomeState(val scrollBehavior: TopAppBarScrollBehavior)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
): HomeState {
    return remember(scrollBehavior) { HomeState(scrollBehavior) }
}
