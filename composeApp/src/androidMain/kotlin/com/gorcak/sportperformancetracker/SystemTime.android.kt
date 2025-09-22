package com.gorcak.sportperformancetracker

actual fun getCurrentTime(): Long {
    return System.currentTimeMillis()
}