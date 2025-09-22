package com.gorcak.sportperformancetracker.core.presentation.util

fun Long.toFormattedTimeDuration(): String {
    val totalSeconds = this / 1000
    val seconds = totalSeconds % 60
    val minutes = (totalSeconds % 3600) / 60
    val hours = totalSeconds / 3600
    return "${hours.toFormatedTimeDigit()}:${minutes.toFormatedTimeDigit()}:${seconds.toFormatedTimeDigit()}"

}

fun Long.toFormatedTimeDigit(): String {
    return when (this) {
        0L -> "00"
        in 1L..9L -> "0$this"
        else -> "$this"
    }
}

fun Int.toFormatedTimeDigit(zeroAsEmpty: Boolean = true): String {
    return if (zeroAsEmpty && this == 0) "" else "$this"
}
