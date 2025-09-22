package com.gorcak.sportperformancetracker.record.data.datasource.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class RecordsDatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<RecordsDatabase> {
        val applicationContext = context.applicationContext

        val dbFile = applicationContext.getDatabasePath(RecordsDatabase.DATABASE_NAME)

        return Room.databaseBuilder(applicationContext, dbFile.absolutePath)
    }
}