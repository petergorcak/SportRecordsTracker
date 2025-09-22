@file:OptIn(ExperimentalForeignApi::class)

package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class RecordsDatabaseFactory {
    actual fun create(): RoomDatabase.Builder<RecordsDatabase> {
        val dbFile = nsDocumentDir() + "/${RecordsDatabase.DATABASE_NAME}"
        return Room.databaseBuilder(dbFile)
    }

    private fun nsDocumentDir() : String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)

    }
}