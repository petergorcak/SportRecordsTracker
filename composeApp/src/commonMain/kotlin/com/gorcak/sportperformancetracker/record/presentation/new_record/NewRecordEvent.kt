package com.gorcak.sportperformancetracker.record.presentation.new_record

sealed interface NewRecordEvent {
    data object RecordCreateSuccess: NewRecordEvent
    data object RecordCreateFailure: NewRecordEvent
}