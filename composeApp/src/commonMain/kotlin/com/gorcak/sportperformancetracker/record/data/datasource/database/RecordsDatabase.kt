package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RecordEntity::class],
    version = 1
)
@ConstructedBy(RecordsDatabaseConstructor::class)
abstract class RecordsDatabase : RoomDatabase() {

    abstract val sportRecordsDao: SportRecordsDao

    companion object {
        const val DATABASE_NAME = "sport_records.db"

    }
}