package com.gorcak.sportperformancetracker.record.presentation.model

import androidx.compose.ui.graphics.Color
import com.gorcak.sportperformancetracker.core.presentation.designsystem.LocalSourceColor
import com.gorcak.sportperformancetracker.core.presentation.designsystem.RemoteSourceColor


enum class SourceColorType(val color: Color) {
    LOCAL(LocalSourceColor), REMOTE(RemoteSourceColor)
}

data class UiRecord(
    val id: String,
    val createdAt: Long,
    val activityTitle: String,
    val locationName: String,
    val formattedDuration: String,
    val sourceColorType: SourceColorType
)