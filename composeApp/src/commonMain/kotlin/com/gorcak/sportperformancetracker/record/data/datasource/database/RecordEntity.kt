package com.gorcak.sportperformancetracker.record.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecordEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val recordTimeStamp: Long,
    val name: String,
    val locationName: String,
    val duration: Long
)
