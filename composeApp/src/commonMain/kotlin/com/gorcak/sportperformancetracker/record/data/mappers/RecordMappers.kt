package com.gorcak.sportperformancetracker.record.data.mappers

import com.gorcak.sportperformancetracker.record.data.datasource.database.RecordEntity
import com.gorcak.sportperformancetracker.record.data.datasource.remote.RecordDto
import com.gorcak.sportperformancetracker.record.domain.Record

fun RecordEntity.toRecord(): Record = Record(
    recordId = id,
    recordTimeStamp = recordTimeStamp,
    name = name,
    locationName = locationName,
    duration = duration
)

fun Record.toRecordEntity(): RecordEntity = RecordEntity(
    id = recordId,
    recordTimeStamp = recordTimeStamp,
    name = name,
    locationName = locationName,
    duration = duration
)

fun Record.toRecordDto(): RecordDto = RecordDto(
    recordId = recordId,
    recordTimeStamp = recordTimeStamp,
    name = name,
    locationName = locationName,
    duration = duration
)
fun RecordDto.toRecord(): Record = Record(
    recordId = recordId,
    recordTimeStamp = recordTimeStamp,
    name = name,
    locationName = locationName,
    duration = duration
)
