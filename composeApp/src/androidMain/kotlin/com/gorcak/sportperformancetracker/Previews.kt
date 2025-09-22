package com.gorcak.sportperformancetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gorcak.sportperformancetracker.core.presentation.designsystem.SportPerformanceTrackerTheme
import com.gorcak.sportperformancetracker.record.presentation.new_record.NewRecordScreen
import com.gorcak.sportperformancetracker.record.presentation.new_record.NewRecordState
import com.gorcak.sportperformancetracker.record.presentation.new_record.composables.TimerInput

@Preview(showSystemUi = true)
@Composable
private fun RecordsListScreenPreview() {
    SportPerformanceTrackerTheme {
        NewRecordScreen  (
            state = NewRecordState(),
            onAction = {}

        )
    }
}

@Preview(showSystemUi = false)
@Composable
private fun TimerInputPreview() {
    SportPerformanceTrackerTheme {
        val focusManager = LocalFocusManager.current
        val focusRequester = FocusRequester.Default

        Box(
            modifier = Modifier
                .background(Color.White).padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            TimerInput  (
                0,
                0,
                0,
                {},
                {},
                {},
                focusManager = focusManager,
                initialFocusRequester = focusRequester,
                isEnabled = true
            )
        }

    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun RecordsListScreenPreview() {
//    SportPerformanceTrackerTheme {
//        RecordsListScreen(
//            state = RecordsListState(),
//            onAction = {}
//
//        )
//    }
//}
//
//
//@Preview(showSystemUi = false)
//@Composable
//private fun RecordPreview() {
//    SportPerformanceTrackerTheme {
//
//        Box(
//            modifier = Modifier
//                .padding(32.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            RecordItem(
//                record = UiRecord(
//                    activityTitle = "Record name  ",
//                    locationName = "Location name ",
//                    formattedDuration = "12:00:45",
//                    id = "1",
//                    createdAt = 123,
//                    sourceColorType = SourceColorType.REMOTE
//                )
//            )
//        }
//
//    }
//}
//
//@Preview(showSystemUi = false)
//@Composable
//private fun RecordPreview2() {
//    SportPerformanceTrackerTheme {
//
//        Box(
//            modifier = Modifier
//                .padding(32.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            RecordItem(
//                record = UiRecord(
//                    activityTitle = "Record name  ",
//                    locationName = "Location name ",
//                    formattedDuration = "12:00:45",
//                    id = "1",
//                    createdAt = 123,
//                    sourceColorType = SourceColorType.LOCAL
//                )
//
//            )
//        }
//
//    }
//}