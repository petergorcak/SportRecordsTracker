package com.gorcak.sportperformancetracker.record.presentation.mappers

import com.gorcak.sportperformancetracker.core.presentation.util.toFormattedTimeDuration
import com.gorcak.sportperformancetracker.record.domain.Record
import com.gorcak.sportperformancetracker.record.presentation.model.SourceColorType
import com.gorcak.sportperformancetracker.record.presentation.model.UiRecord

fun Record.toUiRecord(sourceColorType: SourceColorType) = UiRecord(
    id = recordId,
    createdAt = recordTimeStamp,
    activityTitle = name,
    locationName = locationName,
    formattedDuration = duration.toFormattedTimeDuration(),
    sourceColorType = sourceColorType
)