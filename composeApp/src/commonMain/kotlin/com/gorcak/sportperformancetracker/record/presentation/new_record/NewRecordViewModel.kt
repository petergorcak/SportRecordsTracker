package com.gorcak.sportperformancetracker.record.presentation.new_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorcak.sportperformancetracker.getCurrentTime
import com.gorcak.sportperformancetracker.record.domain.Record
import com.gorcak.sportperformancetracker.record.domain.RecordsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewRecordViewModel(
    private val repository: RecordsRepository
) : ViewModel() {


    private val _events = Channel<NewRecordEvent>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(NewRecordState())
    val state = _state.asStateFlow()

    init {
        state
            .onEach { newRecordState ->
                _state.update {
                    it.copy(canSaveRecord = it.isRecordFormValid() && !it.isSaving)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: NewRecordAction) {

        when (action) {
            NewRecordAction.OnBackClick -> Unit

            is NewRecordAction.OnLocationChanged -> {
                _state.update {
                    it.copy(location = action.location)
                }
            }

            is NewRecordAction.OnNameChanged -> {
                _state.update {
                    it.copy(name = action.name)
                }
            }

            NewRecordAction.OnSaveClick -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            canSaveRecord = false,
                            isSaving = true
                        )
                    }
                    val record = state.value.let {
                        Record(
                            name = it.name,
                            recordId = "id-${getCurrentTime()}",
                            recordTimeStamp = getCurrentTime(),
                            duration = it.durationMillis,
                            locationName = it.location

                        )
                    }
                    // doriesit cancellation
                    repository.saveRecord(record, state.value.useRemoteStorage)
                    // moze vratit result a na jeho zaklade zobrazit error alebo discard screen
                    _state.update {
                        it.copy(
                            name = "",
                            location = "",
                            duration = RecordDuration(0, 0, 0),
                            canSaveRecord = false,
                            isSaving = false
                        )
                    }
                    _events.send(NewRecordEvent.RecordCreateSuccess)
                }
            }

            is NewRecordAction.OnDurationHoursChanged -> {
                _state.update {
                    it.copy(duration = it.duration.copy(hours = action.hours))
                }
            }

            is NewRecordAction.OnDurationMinutesChanged -> {
                _state.update {
                    it.copy(duration = it.duration.copy(minutes = action.minutes))
                }
            }

            is NewRecordAction.OnDurationSecondsChanged -> {
                _state.update {
                    it.copy(duration = it.duration.copy(seconds = action.seconds))
                }
            }

            is NewRecordAction.OnStorageTypeChanged -> {
                _state.update {
                    it.copy(useRemoteStorage = action.useRemoteStorage)
                }
            }
        }
    }

    private fun NewRecordState.isRecordFormValid(): Boolean {
        val nameIsValid = name.isNotBlank() && name.isNotEmpty()
        val locationIsValid = location.isNotBlank() && location.isNotEmpty()
        val durationIsValid = duration.run {
            hours > 0 || minutes > 0 || seconds > 0
        }

        return nameIsValid && locationIsValid && durationIsValid
    }
}