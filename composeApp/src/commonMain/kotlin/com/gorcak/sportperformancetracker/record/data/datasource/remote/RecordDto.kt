package com.gorcak.sportperformancetracker.record.data.datasource.remote

import kotlinx.serialization.Serializable

@Serializable
data class RecordDto(
    val recordId: String,
    val recordTimeStamp: Long,
    val name: String,
    val locationName: String,
    val duration: Long
)
