package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.RoomDatabase

expect class RecordsDatabaseFactory {
    fun create(): RoomDatabase.Builder<RecordsDatabase>
}