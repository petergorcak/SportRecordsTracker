package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.RoomDatabaseConstructor

//@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Suppress("KotlinNoActualForExpect")
expect object RecordsDatabaseConstructor: RoomDatabaseConstructor<RecordsDatabase> {
    override fun initialize(): RecordsDatabase
}