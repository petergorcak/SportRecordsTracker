package com.gorcak.sportperformancetracker.record.domain

import kotlinx.coroutines.flow.Flow


interface RecordsRepository {
    suspend fun saveRecord(data: Record, useRemote: Boolean)
    fun fetchLocalRecords(): Flow<List<Record>>
    fun remoteRecordsFlow(): Flow<List<Record>>
}