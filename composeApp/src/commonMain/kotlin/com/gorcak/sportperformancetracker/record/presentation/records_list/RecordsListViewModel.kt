@file:OptIn(ExperimentalCoroutinesApi::class)

package com.gorcak.sportperformancetracker.record.presentation.records_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorcak.sportperformancetracker.record.domain.Record
import com.gorcak.sportperformancetracker.record.domain.RecordsRepository
import com.gorcak.sportperformancetracker.record.presentation.mappers.toUiRecord
import com.gorcak.sportperformancetracker.record.presentation.model.SourceColorType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecordsListViewModel(
    private val recordsRepository: RecordsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<RecordsListState>(RecordsListState())
    val state = _state.asStateFlow()

    init {
        observeRecords()
    }


    fun onAction(action: RecordsListAction) {
        when (action) {
            RecordsListAction.OnRecordSelected -> Unit

            is RecordsListAction.OnTabSelected -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            selectedTab = action.index
                        )
                    }
                }
            }

            RecordsListAction.AddNewRecord -> Unit
        }
    }

    private fun observeRecords() {
        recordsRepository.fetchLocalRecords()
            .onStart { emit(emptyList<Record>()) }
            .combine(
                recordsRepository.remoteRecordsFlow()
                    .onStart { emit(emptyList<Record>())  }
            ) { localRecords, remoteRecords ->
                _state.update { oldState ->
                    val newLocalRecords = localRecords.map { it.toUiRecord(SourceColorType.LOCAL) }
                        .sortedByDescending { it.createdAt }
                    val newRemoteRecords =
                        remoteRecords.map { it.toUiRecord(SourceColorType.REMOTE) }
                            .sortedByDescending { it.createdAt }
                    oldState.copy(
                        localRecords = newLocalRecords,
                        remoteRecords = newRemoteRecords,
                        allRecords = newRemoteRecords
                            .plus(newLocalRecords)
                            .sortedByDescending { it.createdAt }
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}