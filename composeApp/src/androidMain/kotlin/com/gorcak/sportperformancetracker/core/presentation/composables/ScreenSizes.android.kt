@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.gorcak.sportperformancetracker.core.presentation.composables

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@Composable
actual fun calculateWindowSizeClass(): WindowSizeClass {
    val activity = LocalActivity.current!!
    return calculateWindowSizeClass(activity)
}