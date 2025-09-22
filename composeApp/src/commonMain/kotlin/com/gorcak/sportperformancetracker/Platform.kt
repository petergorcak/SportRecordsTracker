package com.gorcak.sportperformancetracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform