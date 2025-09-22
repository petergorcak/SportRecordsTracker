package com.gorcak.sportperformancetracker.record.presentation.records_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.SdStorage
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gorcak.sportperformancetracker.record.presentation.model.SourceColorType
import com.gorcak.sportperformancetracker.record.presentation.model.UiRecord
import org.jetbrains.compose.resources.stringResource
import sportperformancetracker.composeapp.generated.resources.Res
import sportperformancetracker.composeapp.generated.resources.label_duration
import sportperformancetracker.composeapp.generated.resources.label_location

@Composable
fun RecordItem(
    record: UiRecord,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = record.sourceColorType.color
                ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.activityTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).weight(1f),
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = when (record.sourceColorType) {
                        SourceColorType.LOCAL -> Icons.Outlined.SdStorage
                        SourceColorType.REMOTE -> Icons.Outlined.CloudDone
                    },
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                TitledText(
                    title = stringResource(Res.string.label_location),
                    text = record.locationName,
                    icon = Icons.Outlined.LocationOn
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f ))
                 )

                TitledText(
                    title = stringResource(Res.string.label_duration),
                    text = record.formattedDuration,
                    icon = Icons.Outlined.Timer
                )
            }
        }
    }
}