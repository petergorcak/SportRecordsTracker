package com.gorcak.sportperformancetracker.record.presentation.records_list.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TitledRailItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconVector: ImageVector,
    titleRes: StringResource
) {
    NavigationRailItem(
        selected = isSelected,
        onClick = {
            onClick()
        },
        icon = {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = if(isSelected) 1f else 0.5f
                ),
            )
        },
        label = {
            Text(
                text = stringResource(titleRes),
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = if(isSelected) 1f else 0.5f
                )
            )
        },
        modifier = modifier
    )
}