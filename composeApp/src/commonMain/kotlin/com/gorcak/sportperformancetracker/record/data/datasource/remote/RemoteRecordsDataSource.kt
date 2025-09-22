package com.gorcak.sportperformancetracker.record.data.datasource.remote

import kotlinx.coroutines.flow.Flow

interface RemoteRecordsDataSource {

    suspend fun saveRecord(record: RecordDto)

    fun getRemoteRecordsFlow() : Flow<List<RecordDto>>
}