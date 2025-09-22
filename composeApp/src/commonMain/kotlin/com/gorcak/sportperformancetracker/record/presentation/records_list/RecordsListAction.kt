package com.gorcak.sportperformancetracker.record.presentation.records_list

sealed interface RecordsListAction {
    data object OnRecordSelected: RecordsListAction
    data class OnTabSelected(val index: Int): RecordsListAction
    data object AddNewRecord: RecordsListAction
}