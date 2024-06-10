package com.example.themoviedbapp.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    scrollBehavior: TopAppBarScrollBehavior?,
    title: String?,
    navigationButton: @Composable (() -> Unit)?,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title ?: "") },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    navigationButton?.invoke()
                }
            )
        },
        modifier = Modifier.nestedScroll(
            scrollBehavior?.nestedScrollConnection ?: TopAppBarDefaults.pinnedScrollBehavior().nestedScrollConnection
        ),
        contentWindowInsets = WindowInsets.safeDrawing
    ) { padding ->
        Spacer(modifier = Modifier.padding(top = 6.dp))
        content.invoke(padding)
    }
}

@Composable
fun TopBarNavigationButton(icon: ImageVector, contentDescription: Int, clickAction: () -> Unit) {
    IconButton(onClick = clickAction) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = contentDescription)
        )
    }
}


