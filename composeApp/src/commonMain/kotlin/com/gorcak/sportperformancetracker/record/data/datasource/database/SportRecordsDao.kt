package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SportRecordsDao {

    @Upsert
    suspend fun saveRecord(record: RecordEntity)

    @Query("SELECT * FROM RecordEntity")
    fun getRecords(): Flow<List<RecordEntity>>

    @Query("DELETE FROM RecordEntity WHERE id = :id")
    suspend fun delete(id: String)

}