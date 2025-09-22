package com.gorcak.sportperformancetracker.record.presentation.records_list

import com.gorcak.sportperformancetracker.record.presentation.model.UiRecord

sealed interface RecordsListAction {
    data object OnRecordSelected: RecordsListAction
    data class OnTabSelected(val index: Int): RecordsListAction
    data object AddNewRecord: RecordsListAction
    data class DeleteRecord(val record: UiRecord) : RecordsListAction
}