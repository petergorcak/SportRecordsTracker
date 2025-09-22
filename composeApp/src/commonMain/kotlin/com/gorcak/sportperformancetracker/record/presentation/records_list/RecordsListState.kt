package com.gorcak.sportperformancetracker.record.presentation.records_list

import com.gorcak.sportperformancetracker.record.presentation.model.UiRecord

data class RecordsListState(
    val localRecords: List<UiRecord> = emptyList(),
    val remoteRecords: List<UiRecord> = emptyList(),
    val allRecords: List<UiRecord> = emptyList(),
    val selectedTab: Int = 0,
    val isLoadingRecords: Boolean = false,
)

