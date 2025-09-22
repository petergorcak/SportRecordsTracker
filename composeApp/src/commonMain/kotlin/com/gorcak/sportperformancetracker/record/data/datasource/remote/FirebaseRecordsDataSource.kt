package com.gorcak.sportperformancetracker.record.data.datasource.remote

import com.gorcak.sportperformancetracker.record.domain.Record
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.coroutineContext

class FirebaseRecordsDataSource : RemoteRecordsDataSource {

    private val db = Firebase.database


    override suspend fun saveRecord(record: RecordDto) {
        db
            .reference("sport_records/${record.recordId}")
            .setValue(record)
    }

    override fun getRemoteRecordsFlow(): Flow<List<RecordDto>> {
        val result = db.reference("sport_records").valueEvents
            .map { dataSnapshot ->
                dataSnapshot.children.mapNotNull {
                    try {
                        it.value<RecordDto>()
                    } catch (e: Exception) {

                        coroutineContext.ensureActive()
                        null
                    }
                }
            }
        return result
    }


}