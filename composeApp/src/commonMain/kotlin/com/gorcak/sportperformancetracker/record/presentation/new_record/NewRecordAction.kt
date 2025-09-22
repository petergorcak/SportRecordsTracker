package com.gorcak.sportperformancetracker.record.presentation.new_record

sealed interface NewRecordAction {
    data class OnNameChanged(val name: String) : NewRecordAction
    data class OnLocationChanged(val location: String) : NewRecordAction
    data class OnDurationHoursChanged(val hours: Int): NewRecordAction
    data class OnDurationMinutesChanged(val minutes: Int): NewRecordAction
    data class OnDurationSecondsChanged(val seconds: Int): NewRecordAction
    data object OnBackClick: NewRecordAction
    data object OnSaveClick: NewRecordAction
    data class OnStorageTypeChanged(val useRemoteStorage: Boolean): NewRecordAction
}

