package com.gorcak.sportperformancetracker

import androidx.compose.ui.window.ComposeUIViewController
import com.gorcak.sportperformancetracker.app.App
import com.gorcak.sportperformancetracker.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }