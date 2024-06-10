package com.example.themoviedbapp.screen.home

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.themoviedbapp.common.PermissionRequestEffect
import com.example.themoviedbapp.common.getRegion
import kotlinx.coroutines.launch

/**
 * This class is an state holder of ui created
 * to provides necessary objects for HomeScreen.
 */
@OptIn(ExperimentalMaterial3Api::class)
class HomeState(val scrollBehavior: TopAppBarScrollBehavior) {

    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit, ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
            coroutineScope.launch {
                val region = if (granted) context.getRegion() else "US"
                onRegion(region)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
): HomeState {
    return remember(scrollBehavior) { HomeState(scrollBehavior) }
}
