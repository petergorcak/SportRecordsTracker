@file:OptIn(ExperimentalForeignApi::class, ExperimentalMaterial3WindowSizeClassApi::class)

package com.gorcak.sportperformancetracker.core.presentation.composables

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    val size = UIScreen.mainScreen.bounds.useContents {
        DpSize(size.width.dp, size.height.dp)
    }

    val widthSizeClass = when {
        size.width < 600.dp -> WindowWidthSizeClass.Compact
        size.width < 840.dp -> WindowWidthSizeClass.Medium
        else -> WindowWidthSizeClass.Expanded
    }

    val heightSizeClass = when {
        size.height < 480.dp -> WindowHeightSizeClass.Compact
        size.height < 900.dp -> WindowHeightSizeClass.Medium
        else -> WindowHeightSizeClass.Expanded
    }

    return WindowSizeClass.calculateFromSize(DpSize(size.width, size.height))
}