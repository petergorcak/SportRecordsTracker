package com.gorcak.sportperformancetracker.core.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TrackerScaffold(
    modifier: Modifier = Modifier,
    fab: (@Composable () -> Unit) = {},
    topBar: (@Composable () -> Unit) = {},
    content: (@Composable (PaddingValues) -> Unit) = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        floatingActionButton = { fab() },
        topBar = { topBar() },
    ) { paddingValues ->
        content(paddingValues)
    }
}