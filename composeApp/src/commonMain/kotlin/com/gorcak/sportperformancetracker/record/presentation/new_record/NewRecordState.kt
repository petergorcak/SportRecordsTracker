package com.gorcak.sportperformancetracker.record.presentation.new_record



data class NewRecordState(
    val name: String = "",
    val location: String = "",
    val duration: RecordDuration = RecordDuration(),
    val canSaveRecord: Boolean = false,
    val useRemoteStorage: Boolean = false,
    val isSaving: Boolean = false,
) {
    val durationMillis: Long
        get() = (duration.hours * 60 * 60 + duration.minutes * 60 + duration.seconds) * 1000L
}

data class RecordDuration(
    val hours:Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0
)