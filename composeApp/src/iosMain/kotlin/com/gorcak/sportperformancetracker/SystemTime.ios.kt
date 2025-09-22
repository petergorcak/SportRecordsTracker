package com.gorcak.sportperformancetracker

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual fun getCurrentTime(): Long {
    return (NSDate().timeIntervalSince1970 * 1000).toLong()
}