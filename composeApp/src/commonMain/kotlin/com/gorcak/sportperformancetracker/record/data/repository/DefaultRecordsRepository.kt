package com.gorcak.sportperformancetracker.record.data.repository

import com.gorcak.sportperformancetracker.record.data.datasource.database.SportRecordsDao
import com.gorcak.sportperformancetracker.record.data.datasource.remote.RemoteRecordsDataSource
import com.gorcak.sportperformancetracker.record.data.mappers.toRecord
import com.gorcak.sportperformancetracker.record.data.mappers.toRecordDto
import com.gorcak.sportperformancetracker.record.data.mappers.toRecordEntity
import com.gorcak.sportperformancetracker.record.domain.Record
import com.gorcak.sportperformancetracker.record.domain.RecordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRecordsRepository(
    private val remoteDataSource: RemoteRecordsDataSource,
    private val sportsRecordsDao: SportRecordsDao
) : RecordsRepository {


    override suspend fun saveRecord(data: Record, useRemote: Boolean) {
        if (useRemote) {
            remoteDataSource.saveRecord(data.toRecordDto())
        } else {
            sportsRecordsDao.upsert(data.toRecordEntity())
        }
    }

    override fun fetchLocalRecords(): Flow<List<Record>> {
        return sportsRecordsDao.getRecords()
            .map { recordsEntities -> recordsEntities.map { it.toRecord() } }
    }

    override fun remoteRecordsFlow(): Flow<List<Record>> {
        return remoteDataSource.getRemoteRecordsFlow()
            .map { recordsDto -> recordsDto.map { it.toRecord() } }
    }


}
