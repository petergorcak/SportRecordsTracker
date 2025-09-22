package com.gorcak.sportperformancetracker.record.presentation.records_list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gorcak.sportperformancetracker.record.presentation.model.UiRecord

@Composable
fun RecordsList(
    records: List<UiRecord>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp),
        state = rememberLazyListState()
    ) {
        items(
            items = records,
            key = { it.id }
        ) { record ->
            RecordItem(
                record = record
            )
        }

    }
}