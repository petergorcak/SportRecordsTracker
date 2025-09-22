package com.gorcak.sportperformancetracker.record.domain

data class Record(
    val recordId: String,
    val recordTimeStamp: Long,
    val name: String,
    val locationName: String,
    val duration: Long
)



